package com.mehmetpekdemir.statistics.service.infrastructure.exception;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Integer code;
    private String type;
    private String message;
    private String traceId;
    private String internalMessage;
    private Map<String, String> errors;
}