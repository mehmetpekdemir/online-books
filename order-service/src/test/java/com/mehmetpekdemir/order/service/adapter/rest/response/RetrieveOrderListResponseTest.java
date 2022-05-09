package com.mehmetpekdemir.order.service.adapter.rest.response;

import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveOrderListResponseTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        Order order = Order.builder().build();
        List<Order> orders = List.of(order);

        //when
        RetrieveOrderListResponse retrieveOrderListResponse = RetrieveOrderListResponse.fromModel(orders);

        //then
        assertThat(retrieveOrderListResponse.getOrders()).isNotNull();
    }
}