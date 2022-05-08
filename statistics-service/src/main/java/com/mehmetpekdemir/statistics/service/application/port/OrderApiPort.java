package com.mehmetpekdemir.statistics.service.application.port;

import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;

import java.time.LocalDateTime;

public interface OrderApiPort {

    CustomerOrderStatistics retrieveCustomerOrderStatistics(String customerUid, LocalDateTime startDate, LocalDateTime endDate);
}
