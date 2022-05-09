package com.mehmetpekdemir.order.service.application;

import com.mehmetpekdemir.order.service.application.port.OrderPersistencePort;
import com.mehmetpekdemir.order.service.application.port.StockApiPort;
import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import com.mehmetpekdemir.order.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class OrderFacadeTest extends OrderTest {

    private OrderFacade orderFacade;

    @Mock
    private OrderPersistencePort orderPersistencePort;

    @Mock
    private StockApiPort stockApiPort;

    @BeforeEach
    void setUp() {
        orderFacade = new OrderFacade(orderPersistencePort, stockApiPort);
    }

    @Test
    void should_place_order() {
        //given
        OrderCommand command = OrderCommand.builder()
                .customerUid("customerUid")
                .bookUid("bookUid")
                .amount(3)
                .totalPrice(BigDecimal.valueOf(5))
                .build();

        //when
        orderFacade.placeOrder(command);

        //then
        inOrder.verify(stockApiPort).findAndModify("bookUid", 3);
        inOrder.verify(orderPersistencePort).placeOrder(command);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieve_order() {
        //given
        String orderUid = "orderUid";

        //when
        orderFacade.retrieveOrder(orderUid);

        //then
        inOrder.verify(orderPersistencePort).retrieveOrder(orderUid);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieve_orders() {
        //given
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusMonths(1);

        //when
        orderFacade.retrieveOrders(startDate, endDate);

        //then
        inOrder.verify(orderPersistencePort).retrieveOrders(startDate, endDate);
        inOrder.verifyNoMoreInteractions();
    }
}