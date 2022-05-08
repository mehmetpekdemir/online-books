package com.mehmetpekdemir.book.service.infrastructure.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    private static final String CLIENT_IP = "clientIp";
    private static final String USER_AGENT = "userAgent";

    private static final String NOT_SENT_HEADER_VALUE = "NOT_SENT";
    private static final String CLIENT_IP_HEADER_NAME = "X-Forwarded-For";
    private static final String USER_AGENT_HEADER_NAME = "User-Agent";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        extractAndPutClientIp(request);
        extractAndPutUserAgent(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(CLIENT_IP);
        MDC.remove(USER_AGENT);
    }

    private void extractAndPutClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader(CLIENT_IP_HEADER_NAME);
        if (Strings.isBlank(clientIp)) {
            clientIp = Strings.isBlank(request.getRemoteAddr()) ? NOT_SENT_HEADER_VALUE : request.getRemoteAddr();
        }
        MDC.put(CLIENT_IP, clientIp);
    }

    private void extractAndPutUserAgent(HttpServletRequest request) {
        String userAgent = Strings.isBlank(request.getHeader(USER_AGENT_HEADER_NAME))
                ? NOT_SENT_HEADER_VALUE
                : request.getHeader(USER_AGENT_HEADER_NAME);
        MDC.put(USER_AGENT, userAgent);
    }
}
