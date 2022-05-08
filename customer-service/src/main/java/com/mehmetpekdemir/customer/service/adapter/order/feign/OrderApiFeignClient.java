package com.mehmetpekdemir.customer.service.adapter.order.feign;

import com.mehmetpekdemir.customer.service.adapter.order.feign.response.OrderResponse;
import com.mehmetpekdemir.customer.service.infrastructure.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "order-service", url = "http://localhost:8503", configuration = FeignClientConfiguration.class)
public interface OrderApiFeignClient {

    @GetMapping("/api/v1/internal/customers/{customerUid}/orders")
    OrderResponse retrieveCustomerOrders(@PathVariable String customerUid, @PageableDefault Pageable pageable);
}
