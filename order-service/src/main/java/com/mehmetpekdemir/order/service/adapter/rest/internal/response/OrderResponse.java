package com.mehmetpekdemir.order.service.adapter.rest.internal.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mehmetpekdemir.order.service.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    @JsonUnwrapped
    private Page<Order> orders;

    public static OrderResponse fromModel(Page<Order> orders) {
        return OrderResponse.builder()
                .orders(orders)
                .build();
    }
}
