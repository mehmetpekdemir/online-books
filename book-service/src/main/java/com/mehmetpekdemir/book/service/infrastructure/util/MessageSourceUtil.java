package com.mehmetpekdemir.book.service.infrastructure.util;

import com.mehmetpekdemir.book.service.infrastructure.exception.LocalizedMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSourceUtil {

    private static final int DEFAULT_ERROR_CODE = 9999;
    private static final String DEFAULT_ERROR_MESSAGE = "Sistem Hatası";
    private static final String ERROR_SEPARATOR = ";";

    private final MessageSource messageSource;

    public LocalizedMessageDto getLocalizedMessage(String message, Locale locale) {
        return getLocalizedMessage(message, null, locale);
    }

    public LocalizedMessageDto getLocalizedMessage(String message, Object[] args, Locale locale) {
        LocaleContextHolder.getLocale();
        String defaultMessage = DEFAULT_ERROR_CODE + ERROR_SEPARATOR + message;
        String rawMessage = messageSource.getMessage(message, args, defaultMessage, locale);
        if (StringUtils.isNotBlank(rawMessage)) {
            String[] splitMessage = rawMessage.split(ERROR_SEPARATOR);
            if (splitMessage.length >= 2) {
                int errorCode = Integer.parseInt(splitMessage[0].trim());
                String errorMessage = splitMessage[1].trim();
                return new LocalizedMessageDto(errorCode, errorMessage);
            }
        }
        log.warn("Missing exception key : {}", message);
        return new LocalizedMessageDto(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    public String getLocalizedRawMessage(String message) {
        return messageSource.getMessage(message, null, StringUtils.EMPTY, LocaleContextHolder.getLocale());
    }
}