package com.mehmetpekdemir.statistics.service.adapter.order.feign;

import com.mehmetpekdemir.statistics.service.adapter.order.feign.response.CustomerOrderStatisticsResponse;
import com.mehmetpekdemir.statistics.service.infrastructure.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Service
@FeignClient(name = "order-service", url = "http://localhost:8503", configuration = FeignClientConfiguration.class)
public interface OrderApiFeignClient {

    @GetMapping("/api/v1/internal/customers/{customerUid}/statistics/from/{startDate}/to/{endDate}")
    CustomerOrderStatisticsResponse retrieveCustomerOrderStatistics(@PathVariable String customerUid,
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime startDate,
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime endDate);
}
