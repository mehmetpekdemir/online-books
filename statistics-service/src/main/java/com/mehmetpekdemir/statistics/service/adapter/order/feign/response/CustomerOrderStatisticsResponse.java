package com.mehmetpekdemir.statistics.service.adapter.order.feign.response;

import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Month;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderStatisticsResponse {

    private Month month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedAmount;

    public CustomerOrderStatistics toModel() {
        return CustomerOrderStatistics.builder()
                .month(month)
                .totalOrderCount(totalOrderCount)
                .totalBookCount(totalBookCount)
                .totalPurchasedAmount(totalPurchasedAmount)
                .build();
    }
}
