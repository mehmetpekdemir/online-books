package com.mehmetpekdemir.order.service.domain.order;

import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String orderUid;
    private Integer amount;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;
}
