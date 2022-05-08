package com.mehmetpekdemir.customer.service.adapter.rest.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mehmetpekdemir.customer.service.adapter.rest.response.dto.OrderDto;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveCustomerOrdersResponse {

    @JsonUnwrapped
    private Page<OrderDto> orders;

    public static RetrieveCustomerOrdersResponse fromModel(Page<Order> orders) {
        return RetrieveCustomerOrdersResponse.builder()
                .orders(orders.map(OrderDto::fromModel))
                .build();
    }
}
