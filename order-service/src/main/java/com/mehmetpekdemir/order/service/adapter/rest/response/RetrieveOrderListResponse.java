package com.mehmetpekdemir.order.service.adapter.rest.response;

import com.mehmetpekdemir.order.service.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveOrderListResponse {

    private List<Order> orders;

    public static RetrieveOrderListResponse fromModel(List<Order> orders) {
        return RetrieveOrderListResponse.builder()
                .orders(orders)
                .build();
    }
}
