package com.mehmetpekdemir.order.service.adapter.rest.response;

import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.order.service.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveOrderResponse {

    private String orderUid;
    private Integer amount;
    private BigDecimal totalPrice;
    private OrderStatus orderStatus;

    public static RetrieveOrderResponse fromModel(Order order) {
        return RetrieveOrderResponse.builder()
                .orderUid(order.getOrderUid())
                .amount(order.getAmount())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
