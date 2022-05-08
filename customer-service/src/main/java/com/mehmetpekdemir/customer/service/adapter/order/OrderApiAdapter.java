package com.mehmetpekdemir.customer.service.adapter.order;

import com.mehmetpekdemir.customer.service.adapter.order.feign.OrderApiFeignClient;
import com.mehmetpekdemir.customer.service.adapter.order.feign.response.OrderResponse;
import com.mehmetpekdemir.customer.service.application.port.OrderApiPort;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderApiAdapter implements OrderApiPort {

    private final OrderApiFeignClient orderApiFeignClient;

    @Override
    public Page<Order> retrieveCustomerOrders(String customerUid, Pageable pageable) {
        OrderResponse orderResponse = orderApiFeignClient.retrieveCustomerOrders(customerUid, pageable);
        return orderResponse.getOrders() == null ? Page.empty() : orderResponse.getOrders();
    }
}
