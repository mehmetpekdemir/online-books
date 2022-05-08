package com.mehmetpekdemir.statistics.service.infrastructure.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UidGenerator {

    public static String generateUid() {
        return UUID.randomUUID().toString();
    }
}