package com.mehmetpekdemir.customer.service.domain.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Exception extends RuntimeException {

    private Serializable[] parameters;

    public Exception(String key, Serializable... parameters) {
        super(key);
        this.parameters = parameters;
    }

    public Exception(String key, Throwable cause) {
        super(key, cause);
    }
}