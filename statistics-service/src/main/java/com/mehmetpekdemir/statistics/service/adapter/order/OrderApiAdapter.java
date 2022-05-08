package com.mehmetpekdemir.statistics.service.adapter.order;

import com.mehmetpekdemir.statistics.service.adapter.order.feign.OrderApiFeignClient;
import com.mehmetpekdemir.statistics.service.adapter.order.feign.response.CustomerOrderStatisticsResponse;
import com.mehmetpekdemir.statistics.service.application.port.OrderApiPort;
import com.mehmetpekdemir.statistics.service.domain.statistics.CustomerOrderStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApiAdapter implements OrderApiPort {

    private final OrderApiFeignClient orderApiFeignClient;

    @Override
    public CustomerOrderStatistics retrieveCustomerOrderStatistics(String customerUid, LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Retrieve customer order statistics for customer uid : {} , from :{} to :{} ", customerUid, startDate, endDate);
        CustomerOrderStatisticsResponse response = orderApiFeignClient.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        return response.toModel();
    }
}
