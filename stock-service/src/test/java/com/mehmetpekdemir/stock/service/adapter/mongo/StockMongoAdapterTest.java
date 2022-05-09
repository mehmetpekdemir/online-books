package com.mehmetpekdemir.stock.service.adapter.mongo;

import com.mehmetpekdemir.stock.service.adapter.mongo.document.StockDocument;
import com.mehmetpekdemir.stock.service.adapter.mongo.repository.StockMongoRepository;
import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.stock.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StockMongoAdapterTest extends OrderTest {

    private StockMongoAdapter stockMongoAdapter;

    @Mock
    private StockMongoRepository stockMongoRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        stockMongoAdapter = new StockMongoAdapter(stockMongoRepository, mongoTemplate);
    }

    @Test
    void should_update_book_stock() {
        //given
        StockCommand command = StockCommand.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        when(stockMongoRepository.findByBookUid("bookUid")).thenReturn(Optional.of(new StockDocument()));

        //when
        stockMongoAdapter.updateBookStock(command);

        //then
        inOrder.verify(stockMongoRepository).findByBookUid("bookUid");
        inOrder.verify(stockMongoRepository).save(any());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_retrieve_by_book_uid() {
        //given
        String bookUid = "bookUid";

        when(stockMongoRepository.findByBookUid("bookUid")).thenReturn(Optional.of(new StockDocument()));

        //when
        stockMongoAdapter.retrieveByBookUid(bookUid);

        //then
        inOrder.verify(stockMongoRepository).findByBookUid("bookUid");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_find_and_modify() {
        //given
        String bookUid = "bookUid";
        Integer amount = 1;

        //when
        stockMongoAdapter.findAndModify(bookUid, amount);

        //then
        inOrder.verify(mongoTemplate).findAndModify(any(), any(), any());
        inOrder.verifyNoMoreInteractions();
    }
}