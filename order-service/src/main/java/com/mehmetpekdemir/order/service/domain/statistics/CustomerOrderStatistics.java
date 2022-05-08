package com.mehmetpekdemir.order.service.domain.statistics;

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
public class CustomerOrderStatistics {

    private Month month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private BigDecimal totalPurchasedAmount;
}
