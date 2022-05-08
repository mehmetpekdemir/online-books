package com.mehmetpekdemir.order.service.domain.exception;

import lombok.Getter;

@Getter
public class InternalException extends Exception {

    private final String internalMessage;

    public InternalException(String internalMessage) {
        super("system.exception");
        this.internalMessage = internalMessage;
    }

    public InternalException(Throwable cause) {
        super("system.exception", cause);
        this.internalMessage = cause.getMessage();
    }
}