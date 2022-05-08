package com.mehmetpekdemir.customer.service.adapter.rest.response.dto;

import com.mehmetpekdemir.customer.service.domain.order.Order;
import com.mehmetpekdemir.customer.service.domain.enumtype.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String orderUid;
    private Integer amount;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;

    public static OrderDto fromModel(Order order) {
        return OrderDto.builder()
                .orderUid(order.getOrderUid())
                .amount(order.getAmount())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
