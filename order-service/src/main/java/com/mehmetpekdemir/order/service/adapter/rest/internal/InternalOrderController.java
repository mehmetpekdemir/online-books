package com.mehmetpekdemir.order.service.adapter.rest.internal;

import com.mehmetpekdemir.order.service.adapter.rest.internal.response.CustomerOrderStatisticsResponse;
import com.mehmetpekdemir.order.service.adapter.rest.internal.response.OrderResponse;
import com.mehmetpekdemir.order.service.application.InternalOrderFacade;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.statistics.CustomerOrderStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InternalOrderController {

    private final InternalOrderFacade internalOrderFacade;

    @GetMapping("/v1/internal/customers/{customerUid}/orders")
    public OrderResponse retrieveCustomerOrders(@PathVariable String customerUid, @PageableDefault Pageable pageable) {
        Page<Order> orders = internalOrderFacade.retrieveCustomerOrders(customerUid, pageable);
        return OrderResponse.fromModel(orders);
    }

    @GetMapping("/v1/internal/customers/{customerUid}/statistics/from/{startDate}/to/{endDate}")
    public CustomerOrderStatisticsResponse retrieveCustomerOrderStatistics(@PathVariable String customerUid,
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime startDate,
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime endDate) {
        CustomerOrderStatistics statistics = internalOrderFacade.retrieveCustomerOrderStatistics(customerUid, startDate, endDate);
        return CustomerOrderStatisticsResponse.fromModel(statistics);
    }
}
