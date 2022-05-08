package com.mehmetpekdemir.order.service.infrastructure.handler;

import feign.Response;
import feign.codec.ErrorDecoder;

public class StockApiErrorDecoder implements ErrorDecoder {

    private final Default defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        return defaultDecoder.decode(methodKey, response);
    }
}
