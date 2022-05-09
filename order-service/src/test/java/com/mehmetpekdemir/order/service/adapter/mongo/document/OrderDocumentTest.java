package com.mehmetpekdemir.order.service.adapter.mongo.document;

import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import com.mehmetpekdemir.order.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDocumentTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setUid("uid");
        orderDocument.setAmount(3);
        orderDocument.setTotalPrice(BigDecimal.TEN);
        orderDocument.setStatus(OrderStatus.COMPLETED);

        //when
        Order order = OrderDocument.toModel(orderDocument);

        //then
        assertThat(order.getOrderUid()).isEqualTo("uid");
        assertThat(order.getAmount()).isEqualByComparingTo(3);
        assertThat(order.getTotalPrice()).isEqualByComparingTo(BigDecimal.TEN);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void should_convert_() {
        //given
        OrderCommand command = OrderCommand.builder()
                .customerUid("customerUid")
                .bookUid("bookUid")
                .amount(3)
                .totalPrice(BigDecimal.valueOf(15))
                .build();

        //when
        OrderDocument orderDocument = OrderDocument.fromModel(command);

        //then
        assertThat(orderDocument.getUid()).isNotNull();
        assertThat(orderDocument.getCustomerUid()).isEqualTo("customerUid");
        assertThat(orderDocument.getBookUid()).isEqualTo("bookUid");
        assertThat(orderDocument.getAmount()).isEqualByComparingTo(3);
        assertThat(orderDocument.getTotalPrice()).isEqualByComparingTo(BigDecimal.valueOf(15));
    }
}