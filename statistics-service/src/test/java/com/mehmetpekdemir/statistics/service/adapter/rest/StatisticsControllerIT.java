package com.mehmetpekdemir.statistics.service.adapter.rest;

import com.mehmetpekdemir.statistics.service.adapter.order.feign.response.CustomerOrderStatisticsResponse;
import com.mehmetpekdemir.statistics.service.adapter.rest.response.RetrieveCustomerOrderStatisticsResponse;
import com.mehmetpekdemir.statistics.service.infrastructure.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class StatisticsControllerIT extends BaseIT {

    @Test
    void should_retrieve_customer_order_statistics() {
        //given
        String customerUid = "customerUid";
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusMonths(1);

        CustomerOrderStatisticsResponse response = CustomerOrderStatisticsResponse.builder()
                .month(Month.JANUARY)
                .totalOrderCount(1)
                .totalBookCount(2)
                .totalPurchasedAmount(BigDecimal.TEN)
                .build();

        when(orderApiFeignClient.retrieveCustomerOrderStatistics(customerUid, startDate, endDate)).thenReturn(response);

        //when
        ResponseEntity<RetrieveCustomerOrderStatisticsResponse> responseEntity = testRestTemplate
                .getForEntity("/api/v1/customers/" + customerUid + "/statistics/from/" + startDate + "/to/" + endDate, RetrieveCustomerOrderStatisticsResponse.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        RetrieveCustomerOrderStatisticsResponse body = responseEntity.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMonth()).isEqualByComparingTo(Month.JANUARY);
        assertThat(body.getTotalOrderCount()).isEqualByComparingTo(1);
        assertThat(body.getTotalBookCount()).isEqualByComparingTo(2);
        assertThat(body.getTotalPurchasedAmount()).isEqualByComparingTo(BigDecimal.TEN);
    }
}