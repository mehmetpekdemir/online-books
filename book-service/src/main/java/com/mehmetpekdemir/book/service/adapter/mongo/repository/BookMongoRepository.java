package com.mehmetpekdemir.book.service.adapter.mongo.repository;

import com.mehmetpekdemir.book.service.adapter.mongo.document.BookDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookMongoRepository extends MongoRepository<BookDocument, String> {

    Optional<BookDocument> findByIsbn(String isbn);

    Optional<BookDocument> findByUid(String bookUid);
}
