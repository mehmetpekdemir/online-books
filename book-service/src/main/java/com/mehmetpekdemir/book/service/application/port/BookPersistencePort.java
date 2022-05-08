package com.mehmetpekdemir.book.service.application.port;

import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;

public interface BookPersistencePort {

    boolean existsBookByIsbn(String isbn);

    boolean existsBookByBookUid(String bookUid);

    void createBook(BookCommand command);
}
