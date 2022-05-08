package com.mehmetpekdemir.order.service.adapter.rest.internal.response;

import com.mehmetpekdemir.order.service.domain.statistics.CustomerOrderStatistics;
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

    public static CustomerOrderStatisticsResponse fromModel(CustomerOrderStatistics statistics) {
        return CustomerOrderStatisticsResponse.builder()
                .month(statistics.getMonth())
                .totalOrderCount(statistics.getTotalOrderCount())
                .totalBookCount(statistics.getTotalBookCount())
                .totalPurchasedAmount(statistics.getTotalPurchasedAmount())
                .build();
    }
}
