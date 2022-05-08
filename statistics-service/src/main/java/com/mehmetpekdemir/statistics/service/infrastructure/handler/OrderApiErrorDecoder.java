package com.mehmetpekdemir.statistics.service.infrastructure.handler;

import feign.Response;
import feign.codec.ErrorDecoder;

public class OrderApiErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder.Default defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return defaultDecoder.decode(methodKey, response);
    }
}
