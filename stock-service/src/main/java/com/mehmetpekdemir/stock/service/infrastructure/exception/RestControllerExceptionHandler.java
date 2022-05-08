package com.mehmetpekdemir.stock.service.infrastructure.exception;

import com.mehmetpekdemir.stock.service.domain.exception.BusinessException;
import com.mehmetpekdemir.stock.service.domain.exception.InternalException;
import com.mehmetpekdemir.stock.service.domain.exception.ValidationException;
import com.mehmetpekdemir.stock.service.infrastructure.util.MessageSourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestControllerExceptionHandler {

    private static final String ERROR_LOG_MESSAGE = "An exception occurred during processing of request: {}";

    private final MessageSourceUtil messageSourceUtil;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException exception, Locale locale) {
        log.warn(ERROR_LOG_MESSAGE, getCurrentRequestMethodAndURL(), exception);
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        LocalizedMessageDto localizedMessageDto = messageSourceUtil.getLocalizedMessage(allErrors.get(0).getDefaultMessage(), locale);
        Map<String, String> hashMap = new HashMap<>();
        for (ObjectError error : allErrors) {
            String field = ((FieldError) error).getField();
            String fieldErrorMessage = messageSourceUtil.getLocalizedMessage(error.getDefaultMessage(), locale).getMessage();
            hashMap.put(field, fieldErrorMessage);
        }
        ErrorResponse errorResponse = generateErrorResponse(localizedMessageDto, "ValidationError");
        errorResponse.setErrors(hashMap);
        return errorResponse;
    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException exception, Locale locale) {
        log.warn(ERROR_LOG_MESSAGE, getCurrentRequestMethodAndURL(), exception);
        LocalizedMessageDto localizedMessage = messageSourceUtil.getLocalizedMessage(exception.getMessage(), exception.getParameters(), locale);
        return generateErrorResponse(localizedMessage, "ValidationException");
    }

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException exception, Locale locale) {
        log.warn(ERROR_LOG_MESSAGE, getCurrentRequestMethodAndURL(), exception);
        LocalizedMessageDto localizedMessage = messageSourceUtil.getLocalizedMessage(exception.getMessage(), exception.getParameters(), locale);
        return generateErrorResponse(localizedMessage, "BusinessException");
    }

    @ExceptionHandler(value = {InternalException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalException(InternalException exception, Locale locale) {
        log.error(ERROR_LOG_MESSAGE, getCurrentRequestMethodAndURL(), exception);
        LocalizedMessageDto localizedMessage = messageSourceUtil.getLocalizedMessage(exception.getMessage(), exception.getParameters(), locale);
        return generateErrorResponseForInternal(localizedMessage, "SystemException", exception.getInternalMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception, Locale locale) {
        log.error(ERROR_LOG_MESSAGE, getCurrentRequestMethodAndURL(), exception);
        LocalizedMessageDto localizedMessage = messageSourceUtil.getLocalizedMessage("system.exception", locale);
        return generateErrorResponse(localizedMessage, "SystemException");
    }

    private ErrorResponse generateErrorResponseForInternal(LocalizedMessageDto localizedMessage,
                                                           String exceptionType, String internalMessage) {
        ErrorResponse errorResponse = generateErrorResponse(localizedMessage, exceptionType);
        errorResponse.setInternalMessage(internalMessage);
        return errorResponse;
    }

    private ErrorResponse generateErrorResponse(LocalizedMessageDto localizedMessageDto, String exceptionType) {
        return ErrorResponse.builder()
                .code(localizedMessageDto.getCode())
                .message(localizedMessageDto.getMessage())
                .type(exceptionType)
                .build();
    }

    private String getCurrentRequestMethodAndURL() {
        return getCurrentHttpServletRequest()
                .map(httpServletRequest -> httpServletRequest.getMethod() + ":" + httpServletRequest.getRequestURL())
                .orElse(Strings.EMPTY);
    }

    private Optional<HttpServletRequest> getCurrentHttpServletRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }
}