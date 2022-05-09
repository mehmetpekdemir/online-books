package com.mehmetpekdemir.book.service.application;

import com.mehmetpekdemir.book.service.application.port.BookPersistencePort;
import com.mehmetpekdemir.book.service.application.port.StockApiPort;
import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.domain.exception.BusinessException;
import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.book.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class BookFacadeTest extends OrderTest {

    private BookFacade bookFacade;

    @Mock
    private BookPersistencePort bookPersistencePort;

    @Mock
    private StockApiPort stockApiPort;

    @BeforeEach
    void setUp() {
        bookFacade = new BookFacade(bookPersistencePort, stockApiPort);
    }

    @Test
    void should_create_book() {
        //given
        BookCommand command = BookCommand.builder()
                .isbn("isbn")
                .name("name")
                .authorName("authorName")
                .description("description")
                .build();

        when(bookPersistencePort.existsBookByIsbn("isbn")).thenReturn(false);

        //when
        bookFacade.createBook(command);

        //then
        inOrder.verify(bookPersistencePort).existsBookByIsbn("isbn");
        inOrder.verify(bookPersistencePort).createBook(command);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_create_book_throw_business_exception() {
        //given
        BookCommand command = BookCommand.builder()
                .isbn("isbn")
                .name("name")
                .authorName("authorName")
                .description("description")
                .build();

        when(bookPersistencePort.existsBookByIsbn("isbn")).thenReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> bookFacade.createBook(command));

        //then
        assertThat(throwable).isInstanceOf(BusinessException.class)
                .hasMessageContaining("Isbn already exists");

        inOrder.verify(bookPersistencePort).existsBookByIsbn("isbn");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_update_book_stock() {
        //given
        StockCommand command = StockCommand.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        when(bookPersistencePort.existsBookByBookUid("bookUid")).thenReturn(true);

        //when
        bookFacade.updateBookStock(command);

        //then
        inOrder.verify(bookPersistencePort).existsBookByBookUid("bookUid");
        inOrder.verify(stockApiPort).updateBookStock(command);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_update_book_stock_throw_business_exception() {
        //given
        StockCommand command = StockCommand.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        when(bookPersistencePort.existsBookByBookUid("bookUid")).thenReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> bookFacade.updateBookStock(command));

        //then
        assertThat(throwable).isInstanceOf(BusinessException.class)
                .hasMessageContaining("Book uid doesn't exists");

        inOrder.verify(bookPersistencePort).existsBookByBookUid("bookUid");
        inOrder.verifyNoMoreInteractions();
    }
}