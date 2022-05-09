package com.mehmetpekdemir.book.service.adapter.rest;

import com.mehmetpekdemir.book.service.adapter.mongo.document.BookDocument;
import com.mehmetpekdemir.book.service.adapter.mongo.repository.BookMongoRepository;
import com.mehmetpekdemir.book.service.adapter.rest.request.CreateBookRequest;
import com.mehmetpekdemir.book.service.adapter.rest.request.UpdateBookStockRequest;
import com.mehmetpekdemir.book.service.adapter.stock.feign.request.UpdateStockRequest;
import com.mehmetpekdemir.book.service.domain.enumtype.BookStatus;
import com.mehmetpekdemir.book.service.infrastructure.BaseIT;
import com.mehmetpekdemir.book.service.infrastructure.util.UidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BookControllerIT extends BaseIT {

    @Autowired
    private BookMongoRepository bookMongoRepository;

    @Test
    void should_create_customer() {
        //given
        String isbn = UidGenerator.generateUid() + "isbn";
        CreateBookRequest request = CreateBookRequest.builder()
                .isbn(isbn)
                .name("name")
                .description("description")
                .authorName("authorName")
                .build();

        //when
        ResponseEntity<Void> result = testRestTemplate.postForEntity("/api/v1/books", request, Void.class);

        //then
        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        /*
        Optional<BookDocument> bookDocumentOptional = bookMongoRepository.findByIsbn(isbn);
        assertThat(bookDocumentOptional).isNotNull();

        BookDocument customerDocument = bookDocumentOptional.get();
        assertThat(customerDocument.getIsbn()).isEqualTo(isbn);
        assertThat(customerDocument.getName()).isEqualTo("name");
        assertThat(customerDocument.getDescription()).isEqualTo("description");
        assertThat(customerDocument.getAuthorName()).isEqualTo("authorName");
        assertThat(customerDocument.getStatus()).isEqualTo(BookStatus.ACTIVE);

        assertThat(customerDocument.getId()).isNotNull();
        assertThat(customerDocument.getUid()).isNotNull();
        assertThat(customerDocument.getCreatedAt()).isNotNull();
        assertThat(customerDocument.getUpdatedAt()).isNull();
         */
    }

    @Test
    void should_update_book_stock() {
        //given
        UpdateBookStockRequest request = UpdateBookStockRequest.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        HttpEntity<CreateBookRequest> httpEntity = new HttpEntity(request);

        when(bookPersistencePort.existsBookByBookUid("bookUid")).thenReturn(true);
        doNothing().when(stockApiFeignClient).updateBookStock(UpdateStockRequest.builder().build());

        //when
        ResponseEntity<Void> result = testRestTemplate.exchange("/api/v1/books/stock", HttpMethod.PUT, httpEntity, Void.class);

        //then
        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }
}