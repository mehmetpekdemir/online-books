package com.mehmetpekdemir.order.service.adapter.rest;

import com.mehmetpekdemir.order.service.adapter.rest.request.CreateOrderRequest;
import com.mehmetpekdemir.order.service.adapter.rest.response.RetrieveOrderListResponse;
import com.mehmetpekdemir.order.service.adapter.rest.response.RetrieveOrderResponse;
import com.mehmetpekdemir.order.service.application.OrderFacade;
import com.mehmetpekdemir.order.service.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping("/v1/orders")
    public void placeOrder(@RequestBody @Valid CreateOrderRequest request) {
        orderFacade.placeOrder(request.toModel());
    }

    @GetMapping("/v1/orders/{orderUid}")
    public RetrieveOrderResponse retrieveOrder(@PathVariable String orderUid) {
        Order order = orderFacade.retrieveOrder(orderUid);
        return RetrieveOrderResponse.fromModel(order);
    }

    @GetMapping("/v1/orders/from/{startDate}/to/{endDate}")
    public RetrieveOrderListResponse retrieveOrders(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime startDate,
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @PathVariable LocalDateTime endDate) {
        List<Order> orders = orderFacade.retrieveOrders(startDate, endDate);
        return RetrieveOrderListResponse.fromModel(orders);
    }
}
