package com.mehmetpekdemir.order.service.infrastructure.config;

import com.mehmetpekdemir.order.service.infrastructure.handler.StockApiErrorDecoder;
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
    public ErrorDecoder stockApiErrorDecoder() {
        return new StockApiErrorDecoder();
    }
}
