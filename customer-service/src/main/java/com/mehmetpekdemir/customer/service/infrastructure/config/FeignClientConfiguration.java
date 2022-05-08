package com.mehmetpekdemir.customer.service.infrastructure.config;

import com.mehmetpekdemir.customer.service.infrastructure.handler.OrderApiErrorDecoder;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public ErrorDecoder orderApiErrorDecoder() {
        return new OrderApiErrorDecoder();
    }
}
