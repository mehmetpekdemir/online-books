package com.mehmetpekdemir.stock.service.adapter.mongo.repository;

import com.mehmetpekdemir.stock.service.adapter.mongo.document.StockDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockMongoRepository extends MongoRepository<StockDocument, String> {

    Optional<StockDocument> findByBookUid(String bookUid);
}
