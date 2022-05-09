package com.mehmetpekdemir.stock.service.application;

import com.mehmetpekdemir.stock.service.adapter.mongo.document.StockDocument;
import com.mehmetpekdemir.stock.service.application.port.StockPersistencePort;
import com.mehmetpekdemir.stock.service.domain.exception.BusinessException;
import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.stock.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class InternalStockFacadeTest extends OrderTest {

    private InternalStockFacade internalStockFacade;

    @Mock
    private StockPersistencePort stockPersistencePort;

    @BeforeEach
    void setUp() {
        internalStockFacade = new InternalStockFacade(stockPersistencePort);
    }

    @Test
    void should_update_book_stock() {
        //given
        StockCommand command = StockCommand.builder().build();

        //when
        internalStockFacade.updateBookStock(command);

        //then
        inOrder.verify(stockPersistencePort).updateBookStock(command);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_find_and_modify_success_scenario() {
        //given
        String bookUid = "bookUid";
        Integer amount = 3;

        StockDocument stockDocument = new StockDocument();
        stockDocument.setAmount(5);

        when(stockPersistencePort.retrieveByBookUid(bookUid)).thenReturn(Optional.of(stockDocument));

        //when
        internalStockFacade.findAndModify(bookUid, amount);

        //then
        inOrder.verify(stockPersistencePort).retrieveByBookUid(bookUid);
        inOrder.verify(stockPersistencePort).findAndModify(bookUid, 2);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_find_and_modify_throw_book_not_found() {
        //given
        String bookUid = "bookUid";
        Integer amount = 3;

        StockDocument stockDocument = new StockDocument();
        stockDocument.setAmount(5);

        when(stockPersistencePort.retrieveByBookUid(bookUid)).thenReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> internalStockFacade.findAndModify(bookUid, amount));

        //then
        assertThat(throwable).isInstanceOf(BusinessException.class)
                .hasMessageContaining("Book not found !");

        inOrder.verify(stockPersistencePort).retrieveByBookUid(bookUid);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_find_and_modify_throw_insufficient_stock_exception() {
        //given
        String bookUid = "bookUid";
        Integer amount = 10;

        StockDocument stockDocument = new StockDocument();
        stockDocument.setAmount(5);

        when(stockPersistencePort.retrieveByBookUid(bookUid)).thenReturn(Optional.of(stockDocument));

        //when
        Throwable throwable = catchThrowable(() -> internalStockFacade.findAndModify(bookUid, amount));

        //then
        assertThat(throwable).isInstanceOf(BusinessException.class)
                .hasMessageContaining("Insufficient stock exception !");

        inOrder.verify(stockPersistencePort).retrieveByBookUid(bookUid);
        inOrder.verifyNoMoreInteractions();
    }
}