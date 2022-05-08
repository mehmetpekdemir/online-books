package com.mehmetpekdemir.customer.service.application.port;

import com.mehmetpekdemir.customer.service.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderApiPort {

    Page<Order> retrieveCustomerOrders(String customerUid, Pageable pageable);
}
