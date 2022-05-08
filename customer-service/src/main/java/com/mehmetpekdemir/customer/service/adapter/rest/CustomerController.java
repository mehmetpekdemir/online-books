package com.mehmetpekdemir.customer.service.adapter.rest;

import com.mehmetpekdemir.customer.service.adapter.rest.request.CreateCustomerRequest;
import com.mehmetpekdemir.customer.service.adapter.rest.response.RetrieveCustomerOrdersResponse;
import com.mehmetpekdemir.customer.service.application.CustomerFacade;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerFacade customerFacade;

    @PostMapping("/v1/customers")
    public void createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        customerFacade.createCustomer(request.toModel());
    }

    @GetMapping("/v1/customers/{customerUid}/orders")
    public RetrieveCustomerOrdersResponse retrieveCustomerOrders(@PathVariable String customerUid, @PageableDefault Pageable pageable) {
        Page<Order> orders = customerFacade.retrieveCustomerOrders(customerUid, pageable);
        return RetrieveCustomerOrdersResponse.fromModel(orders);
    }
}
