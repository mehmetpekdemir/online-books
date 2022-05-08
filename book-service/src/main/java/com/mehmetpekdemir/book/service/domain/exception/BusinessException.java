package com.mehmetpekdemir.book.service.domain.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class BusinessException extends Exception {

    public BusinessException(String key) {
        super(key);
    }

    public BusinessException(String key, Serializable... parameters) {
        super(key, parameters);
    }
}