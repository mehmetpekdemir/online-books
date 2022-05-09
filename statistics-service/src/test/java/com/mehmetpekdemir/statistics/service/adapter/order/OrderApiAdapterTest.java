package com.mehmetpekdemir.statistics.service.adapter.order;

import com.mehmetpekdemir.statistics.service.adapter.order.feign.OrderApiFeignClient;
import com.mehmetpekdemir.statistics.service.adapter.order.feign.response.CustomerOrderStatisticsResponse;
import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import com.mehmetpekdemir.statistics.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
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
    void should_Retrieve_customer_order_statistics() {
        //given
        String customerUid = "customerUid";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusMonths(1);

        CustomerOrderStatisticsResponse response = CustomerOrderStatisticsResponse.builder()
                .totalOrderCount(1)
                .totalBookCount(1)
                .totalPurchasedAmount(BigDecimal.valueOf(50))
                .month(Month.JANUARY)
                .build();

        when(orderApiFeignClient.retrieveCustomerOrderStatistics(customerUid, startDate, endDate)).thenReturn(response);

        //when
        CustomerOrderStatistics statistics = orderApiAdapter.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);

        //then
        assertThat(statistics.getMonth()).isEqualByComparingTo(Month.JANUARY);
        assertThat(statistics.getTotalBookCount()).isEqualByComparingTo(1);
        assertThat(statistics.getTotalPurchasedAmount()).isEqualByComparingTo(BigDecimal.valueOf(50));
        assertThat(statistics.getTotalOrderCount()).isEqualByComparingTo(1);

        inOrder.verify(orderApiFeignClient).retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        inOrder.verifyNoMoreInteractions();
    }
}