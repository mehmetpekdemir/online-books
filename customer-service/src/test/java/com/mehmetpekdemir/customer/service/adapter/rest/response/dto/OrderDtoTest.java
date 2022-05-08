package com.mehmetpekdemir.customer.service.adapter.rest.response.dto;

import com.mehmetpekdemir.customer.service.infrastructure.BaseTest;
import com.mehmetpekdemir.customer.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDtoTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        Order order = Order.builder()
                .orderUid("orderUid")
                .amount(1)
                .totalPrice(BigDecimal.ONE)
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        //when
        OrderDto orderDto = OrderDto.fromModel(order);

        //then
        assertThat(orderDto.getOrderUid()).isEqualTo("orderUid");
        assertThat(orderDto.getAmount()).isEqualByComparingTo(1);
        assertThat(orderDto.getTotalPrice()).isEqualByComparingTo(BigDecimal.ONE);
        assertThat(orderDto.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }
}