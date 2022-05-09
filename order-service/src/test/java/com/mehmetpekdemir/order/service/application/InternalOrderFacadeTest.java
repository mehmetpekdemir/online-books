package com.mehmetpekdemir.order.service.application;

import com.mehmetpekdemir.order.service.application.port.OrderPersistencePort;
import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.statistics.CustomerOrderStatistics;
import com.mehmetpekdemir.order.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class InternalOrderFacadeTest extends OrderTest {

    private InternalOrderFacade internalOrderFacade;

    @Mock
    private OrderPersistencePort orderPersistencePort;

    @BeforeEach
    void setUp() {
        internalOrderFacade = new InternalOrderFacade(orderPersistencePort);
    }

    @Test
    void should_retrieve_customer_orders() {
        //given
        String customerUid = "customerUid";
        PageRequest request = PageRequest.of(0, 5);

        //when
        internalOrderFacade.retrieveCustomerOrders(customerUid, request);

        //then
        inOrder.verify(orderPersistencePort).retrieveCustomerOrders(customerUid, request);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieve_customer_order_statistics() {
        //given
        String customerUid = "customerUid";
        LocalDateTime startDate = LocalDateTime.of(2022, 3, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1);

        Order order = Order.builder()
                .orderUid("orderUid")
                .totalPrice(BigDecimal.ONE)
                .amount(3)
                .orderStatus(OrderStatus.COMPLETED)
                .build();

        when(orderPersistencePort.retrieveCustomerOrderStatistics(customerUid, startDate, endDate)).thenReturn(List.of(order));

        //when
        CustomerOrderStatistics customerOrderStatistics = internalOrderFacade.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);

        //then
        inOrder.verify(orderPersistencePort).retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        inOrder.verifyNoMoreInteractions();

        assertThat(customerOrderStatistics.getMonth()).isEqualByComparingTo(Month.MARCH);
        assertThat(customerOrderStatistics.getTotalOrderCount()).isEqualByComparingTo(1);
        assertThat(customerOrderStatistics.getTotalPurchasedAmount()).isEqualByComparingTo(BigDecimal.ONE);
        assertThat(customerOrderStatistics.getTotalBookCount()).isEqualByComparingTo(3);
    }
}
