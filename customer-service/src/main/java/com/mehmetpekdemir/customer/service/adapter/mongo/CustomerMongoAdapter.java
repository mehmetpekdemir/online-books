package com.mehmetpekdemir.customer.service.adapter.mongo;

import com.mehmetpekdemir.customer.service.adapter.mongo.document.CustomerDocument;
import com.mehmetpekdemir.customer.service.adapter.mongo.repository.CustomerMongoRepository;
import com.mehmetpekdemir.customer.service.application.port.CustomerPersistencePort;
import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.domain.enumtype.CustomerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerMongoAdapter implements CustomerPersistencePort {

    private final CustomerMongoRepository customerMongoRepository;

    @Override
    public boolean existsCustomer(String email) {
        log.info("Validating already exists customer for email: {} ", email);
        return customerMongoRepository.findByEmail(email).isPresent();
    }

    @Override
    public void createCustomer(CustomerCommand command) {
        log.info("Started create customer for email: {} ", command.getEmail());
        CustomerDocument customerDocument = CustomerDocument.fromModel(command);
        customerDocument.setCreatedAt(LocalDateTime.now());
        customerDocument.setStatus(CustomerStatus.ACTIVE);
        customerMongoRepository.save(customerDocument);
    }
}
