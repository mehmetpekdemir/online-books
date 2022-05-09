package com.mehmetpekdemir.order.service.adapter.rest.request;

import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import com.mehmetpekdemir.order.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CreateOrderRequestTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CreateOrderRequest request = CreateOrderRequest.builder()
                .customerUid("customerUid")
                .bookUid("bookUid")
                .amount(3)
                .totalPrice(BigDecimal.valueOf(12))
                .build();

        //when
        OrderCommand orderCommand = request.toModel();

        //then
        assertThat(orderCommand.getCustomerUid()).isEqualTo("customerUid");
        assertThat(orderCommand.getBookUid()).isEqualTo("bookUid");
        assertThat(orderCommand.getAmount()).isEqualByComparingTo(3);
        assertThat(orderCommand.getTotalPrice()).isEqualTo(BigDecimal.valueOf(12));
    }
}