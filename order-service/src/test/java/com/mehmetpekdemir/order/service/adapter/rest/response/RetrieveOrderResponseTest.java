package com.mehmetpekdemir.order.service.adapter.rest.response;

import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveOrderResponseTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        Order order = Order.builder()
                .orderUid("orderUid")
                .amount(3)
                .totalPrice(BigDecimal.valueOf(3))
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        //when
        RetrieveOrderResponse retrieveOrderResponse = RetrieveOrderResponse.fromModel(order);

        //then
        assertThat(retrieveOrderResponse.getOrderUid()).isEqualTo("orderUid");
        assertThat(retrieveOrderResponse.getAmount()).isEqualByComparingTo(3);
        assertThat(retrieveOrderResponse.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(3));
        assertThat(retrieveOrderResponse.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }
}