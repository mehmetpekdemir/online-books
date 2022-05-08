package com.mehmetpekdemir.book.service.adapter.mongo;

import com.mehmetpekdemir.book.service.adapter.mongo.document.BookDocument;
import com.mehmetpekdemir.book.service.adapter.mongo.repository.BookMongoRepository;
import com.mehmetpekdemir.book.service.application.port.BookPersistencePort;
import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.domain.enumtype.BookStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMongoAdapter implements BookPersistencePort {

    private final BookMongoRepository bookMongoRepository;

    @Override
    public boolean existsBookByIsbn(String isbn) {
        log.info("Validating already exists book for isbn: {} ", isbn);
        return bookMongoRepository.findByIsbn(isbn).isPresent();
    }

    @Override
    public boolean existsBookByBookUid(String bookUid) {
        log.info("Validating already exists book for book uid: {} ", bookUid);
        return bookMongoRepository.findByUid(bookUid).isPresent();
    }

    @Override
    public void createBook(BookCommand command) {
        log.info("Started create book for isbn: {} ", command.getIsbn());
        BookDocument customerDocument = BookDocument.fromModel(command);
        customerDocument.setCreatedAt(LocalDateTime.now());
        customerDocument.setStatus(BookStatus.ACTIVE);
        bookMongoRepository.save(customerDocument);
    }
}
