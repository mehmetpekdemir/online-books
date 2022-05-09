package com.mehmetpekdemir.statistics.service.adapter.order.feign.response;

import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import com.mehmetpekdemir.statistics.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerOrderStatisticsResponseTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CustomerOrderStatisticsResponse orderStatisticsResponse = CustomerOrderStatisticsResponse.builder()
                .month(Month.JANUARY)
                .totalOrderCount(1)
                .totalBookCount(2)
                .totalPurchasedAmount(BigDecimal.TEN)
                .build();

        //when
        CustomerOrderStatistics statisticsResponse = orderStatisticsResponse.toModel();

        //then
        assertThat(statisticsResponse.getMonth()).isEqualByComparingTo(Month.JANUARY);
        assertThat(statisticsResponse.getTotalOrderCount()).isEqualByComparingTo(1);
        assertThat(statisticsResponse.getTotalBookCount()).isEqualByComparingTo(2);
        assertThat(statisticsResponse.getTotalPurchasedAmount()).isEqualByComparingTo(BigDecimal.TEN);
    }
}