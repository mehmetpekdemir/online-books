package com.mehmetpekdemir.customer.service.application.port;

import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;

public interface CustomerPersistencePort {

    boolean existsCustomer(String email);

    void createCustomer(CustomerCommand command);
}
