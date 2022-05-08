package com.mehmetpekdemir.statistics.service.adapter.rest.response;

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
public class RetrieveCustomerOrderStatisticsResponse {

    private Month month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedAmount;

    public static RetrieveCustomerOrderStatisticsResponse fromModel(CustomerOrderStatistics statistics) {
        return RetrieveCustomerOrderStatisticsResponse.builder()
                .month(statistics.getMonth())
                .totalOrderCount(statistics.getTotalOrderCount())
                .totalBookCount(statistics.getTotalBookCount())
                .totalPurchasedAmount(statistics.getTotalPurchasedAmount())
                .build();
    }
}
