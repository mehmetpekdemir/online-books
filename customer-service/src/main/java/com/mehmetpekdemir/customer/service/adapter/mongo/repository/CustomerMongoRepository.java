package com.mehmetpekdemir.customer.service.adapter.mongo.repository;

import com.mehmetpekdemir.customer.service.adapter.mongo.document.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerMongoRepository extends MongoRepository<CustomerDocument, String> {

    Optional<CustomerDocument> findByEmail(String email);
}
