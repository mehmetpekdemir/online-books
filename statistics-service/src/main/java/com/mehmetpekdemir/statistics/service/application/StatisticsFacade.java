package com.mehmetpekdemir.statistics.service.application;

import com.mehmetpekdemir.statistics.service.application.port.OrderApiPort;
import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatisticsFacade {

    private final OrderApiPort orderApiPort;

    public CustomerOrderStatistics retrieveCustomerOrderStatistics(String customerUid, LocalDateTime startDate, LocalDateTime endDate) {
        return orderApiPort.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
    }
}
