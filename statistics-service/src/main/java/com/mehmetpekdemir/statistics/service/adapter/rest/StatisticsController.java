package com.mehmetpekdemir.statistics.service.adapter.rest;

import com.mehmetpekdemir.statistics.service.adapter.rest.response.RetrieveCustomerOrderStatisticsResponse;
import com.mehmetpekdemir.statistics.service.application.StatisticsFacade;
import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsFacade statisticsFacade;

    @GetMapping("/v1/customers/{customerUid}/statistics/from/{startDate}/to/{endDate}")
    public RetrieveCustomerOrderStatisticsResponse retrieveCustomerOrderStatistics(@PathVariable String customerUid,
                                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime startDate,
                                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime endDate) {
        CustomerOrderStatistics statistics = statisticsFacade.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        return RetrieveCustomerOrderStatisticsResponse.fromModel(statistics);
    }
}
