package com.mehmetpekdemir.customer.service.adapter.order;

import com.mehmetpekdemir.customer.service.adapter.order.feign.OrderApiFeignClient;
import com.mehmetpekdemir.customer.service.adapter.order.feign.response.OrderResponse;
import com.mehmetpekdemir.customer.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import com.mehmetpekdemir.customer.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class OrderApiAdapterTest extends OrderTest {

    private OrderApiAdapter orderApiAdapter;

    @Mock
    private OrderApiFeignClient orderApiFeignClient;

    @BeforeEach
    void setUp() {
        orderApiAdapter = new OrderApiAdapter(orderApiFeignClient);
    }

    @Test
    void should_retrieve_customer_orders_is_not_empty() {
        //given
        String customerUid = "customerUid";
        PageRequest pageRequest = PageRequest.of(0, 100);

        Order order = Order.builder()
                .orderUid("orderUid")
                .totalPrice(BigDecimal.TEN)
                .amount(2)
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        Page<Order> orders = new PageImpl<>(List.of(order));

        OrderResponse orderResponse = OrderResponse.builder()
                .orders(orders)
                .build();

        when(orderApiFeignClient.retrieveCustomerOrders(customerUid, pageRequest)).thenReturn(orderResponse);

        //when
        Page<Order> response = orderApiAdapter.retrieveCustomerOrders(customerUid, pageRequest);

        //then

        assertThat(response).isNotNull();
        List<Order> content = response.getContent();
        assertThat(content.size()).isEqualByComparingTo(1);
        Order responseOrder = content.get(0);
        assertThat(responseOrder.getOrderUid()).isEqualTo("orderUid");
        assertThat(responseOrder.getTotalPrice()).isEqualByComparingTo(BigDecimal.TEN);
        assertThat(responseOrder.getAmount()).isEqualByComparingTo(2);
        assertThat(responseOrder.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void should_retrieve_customer_orders_is_empty() {
        //given
        String customerUid = "customerUid";
        PageRequest pageRequest = PageRequest.of(0, 100);

        Page<Order> orders = new PageImpl<>(Collections.emptyList());

        OrderResponse orderResponse = OrderResponse.builder()
                .orders(orders)
                .build();

        when(orderApiFeignClient.retrieveCustomerOrders(customerUid, pageRequest)).thenReturn(orderResponse);

        //when
        Page<Order> response = orderApiAdapter.retrieveCustomerOrders(customerUid, pageRequest);

        //then
        assertThat(response).isNotNull();
        List<Order> content = response.getContent();
        assertThat(content.size()).isEqualByComparingTo(0);
    }
}