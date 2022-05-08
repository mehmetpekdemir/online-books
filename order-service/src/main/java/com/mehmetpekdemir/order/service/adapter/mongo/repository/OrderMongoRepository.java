package com.mehmetpekdemir.order.service.adapter.mongo.repository;

import com.mehmetpekdemir.order.service.adapter.mongo.document.OrderDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderMongoRepository extends MongoRepository<OrderDocument, String> {

    Page<OrderDocument> findAllByCustomerUidOrderByCreatedAtDesc(String customerUid, Pageable pageable);

    Optional<OrderDocument> findByUid(String orderUid);

    List<OrderDocument> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDocument> findOrderDocumentByCustomerUidAndCreatedAtBetween(String customerUid, LocalDateTime startDate, LocalDateTime endDate);
}
