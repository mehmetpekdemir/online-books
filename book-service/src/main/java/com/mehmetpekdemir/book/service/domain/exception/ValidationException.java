package com.mehmetpekdemir.book.service.domain.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ValidationException extends Exception {

    public ValidationException(String key) {
        super(key);
    }

    public ValidationException(String key, Serializable... parameters) {
        super(key, parameters);
    }
}