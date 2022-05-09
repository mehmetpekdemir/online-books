package com.mehmetpekdemir.statistics.service.application;

import com.mehmetpekdemir.statistics.service.application.port.OrderApiPort;
import com.mehmetpekdemir.statistics.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

class StatisticsFacadeTest extends OrderTest {

    private StatisticsFacade statisticsFacade;

    @Mock
    private OrderApiPort orderApiPort;

    @BeforeEach
    void setUp() {
        statisticsFacade = new StatisticsFacade(orderApiPort);
    }

    @Test
    void should_retrieve_customer_order_statistics() {
        //given
        String customerUid = "customerUid";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusMonths(1);

        //when
        statisticsFacade.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);

        //then
        inOrder.verify(orderApiPort).retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        inOrder.verifyNoMoreInteractions();
    }
}