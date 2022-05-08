package com.mehmetpekdemir.customer.service.application;

import com.mehmetpekdemir.customer.service.application.port.CustomerPersistencePort;
import com.mehmetpekdemir.customer.service.application.port.OrderApiPort;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFacade {

    private final CustomerPersistencePort customerPersistencePort;
    private final OrderApiPort orderApiPort;

    public void createCustomer(CustomerCommand command) {
        ifExistsCustomerThrowBusinessException(command.getEmail());
        create(command);
    }

    private void ifExistsCustomerThrowBusinessException(String email) {
        if (customerPersistencePort.existsCustomer(email)) {
            throw new BusinessException("Email already exists");
        }
    }

    private void create(CustomerCommand command) {
        customerPersistencePort.createCustomer(command);
    }

    public Page<Order> retrieveCustomerOrders(String customerUid, Pageable pageable) {
        return orderApiPort.retrieveCustomerOrders(customerUid, pageable);
    }
}
