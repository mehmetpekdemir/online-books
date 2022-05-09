package com.mehmetpekdemir.statistics.service.adapter.rest.response;

import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import com.mehmetpekdemir.statistics.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveCustomerOrderStatisticsResponseTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CustomerOrderStatistics statistics = CustomerOrderStatistics.builder()
                .month(Month.JANUARY)
                .totalPurchasedAmount(BigDecimal.valueOf(20))
                .totalBookCount(10)
                .totalOrderCount(5)
                .build();

        //when
        RetrieveCustomerOrderStatisticsResponse response = RetrieveCustomerOrderStatisticsResponse.fromModel(statistics);

        //then
        assertThat(response.getMonth()).isEqualByComparingTo(Month.JANUARY);
        assertThat(response.getTotalPurchasedAmount()).isEqualByComparingTo(BigDecimal.valueOf(20));
        assertThat(response.getTotalBookCount()).isEqualByComparingTo(10);
        assertThat(response.getTotalOrderCount()).isEqualByComparingTo(5);
    }
}