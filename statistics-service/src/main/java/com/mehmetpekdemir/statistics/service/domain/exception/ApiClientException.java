package com.mehmetpekdemir.statistics.service.domain.exception;

import lombok.Getter;

@Getter
public class ApiClientException extends Exception {

    private final String content;

    public ApiClientException(String content) {
        super("system.exception");
        this.content = content;
    }
}