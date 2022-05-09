package com.mehmetpekdemir.order.service.adapter.rest.internal.response;

import com.mehmetpekdemir.order.service.domain.statistics.CustomerOrderStatistics;
import com.mehmetpekdemir.order.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerOrderStatisticsResponseTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CustomerOrderStatistics statistics = CustomerOrderStatistics.builder()
                .month(Month.JANUARY)
                .totalOrderCount(3)
                .totalBookCount(5)
                .totalPurchasedAmount(BigDecimal.valueOf(55))
                .build();

        //when
        CustomerOrderStatisticsResponse customerOrderStatisticsResponse = CustomerOrderStatisticsResponse.fromModel(statistics);

        //then
        assertThat(customerOrderStatisticsResponse.getMonth()).isEqualByComparingTo(Month.JANUARY);
        assertThat(customerOrderStatisticsResponse.getTotalOrderCount()).isEqualByComparingTo(3);
        assertThat(customerOrderStatisticsResponse.getTotalBookCount()).isEqualByComparingTo(5);
        assertThat(customerOrderStatisticsResponse.getTotalPurchasedAmount()).isEqualByComparingTo(BigDecimal.valueOf(55));
    }
}