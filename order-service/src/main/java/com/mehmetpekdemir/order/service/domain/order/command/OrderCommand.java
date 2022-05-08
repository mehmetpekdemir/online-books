package com.mehmetpekdemir.order.service.domain.order.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCommand {

    private String customerUid;
    private String bookUid;
    private Integer amount;
    private BigDecimal totalPrice;
}
