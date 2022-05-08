package com.mehmetpekdemir.stock.service.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalizedMessageDto {

    private Integer code;
    private String message;
}