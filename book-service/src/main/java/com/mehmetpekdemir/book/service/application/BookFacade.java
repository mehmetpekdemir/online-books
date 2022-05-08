package com.mehmetpekdemir.book.service.application;

import com.mehmetpekdemir.book.service.application.port.BookPersistencePort;
import com.mehmetpekdemir.book.service.application.port.StockApiPort;
import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.domain.exception.BusinessException;
import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacade {

    private final BookPersistencePort bookPersistencePort;
    private final StockApiPort stockApiPort;

    public void createBook(BookCommand command) {
        ifExistsIsbnThrowBusinessException(command.getIsbn());
        create(command);
    }

    private void ifExistsIsbnThrowBusinessException(String isbn) {
        if (bookPersistencePort.existsBookByIsbn(isbn)) {
            throw new BusinessException("Isbn already exists");
        }
    }

    private void create(BookCommand command) {
        bookPersistencePort.createBook(command);
    }

    public void updateBookStock(StockCommand command) {
        ifDoesNotExistsBookUidThrowBusinessException(command.getBookUid());
        update(command);
    }

    private void ifDoesNotExistsBookUidThrowBusinessException(String bookUid) {
        if (!bookPersistencePort.existsBookByBookUid(bookUid)) {
            throw new BusinessException("Book uid doesn't exists");
        }
    }

    private void update(StockCommand command) {
        stockApiPort.updateBookStock(command);
    }
}
