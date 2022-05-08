package com.mehmetpekdemir.order.service.application.port;

import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderPersistencePort {

    Page<Order> retrieveCustomerOrders(String customerUid, Pageable pageable);

    void placeOrder(OrderCommand command);

    Order retrieveOrder(String orderUid);

    List<Order> retrieveOrders(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> retrieveCustomerOrderStatistics(String customerUid, LocalDateTime startDate, LocalDateTime endDate);
}
