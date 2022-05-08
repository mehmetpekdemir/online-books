package com.mehmetpekdemir.order.service.application;

import com.mehmetpekdemir.order.service.application.port.OrderPersistencePort;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.statistics.CustomerOrderStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternalOrderFacade {

    private final OrderPersistencePort orderPersistencePort;

    public Page<Order> retrieveCustomerOrders(String customerUid, Pageable pageable) {
        return orderPersistencePort.retrieveCustomerOrders(customerUid, pageable);
    }

    public CustomerOrderStatistics retrieveCustomerOrderStatistics(String customerUid, LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderPersistencePort.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        return prepare(orders, startDate);
    }

    private CustomerOrderStatistics prepare(List<Order> orders, LocalDateTime startDate) {
        return CustomerOrderStatistics.builder()
                .month(startDate.getMonth())
                .totalOrderCount(orders.size())
                .totalBookCount(orders.stream().map(Order::getAmount).reduce(0, Integer::sum))
                .totalPurchasedAmount(orders.stream().map(Order::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }
}
