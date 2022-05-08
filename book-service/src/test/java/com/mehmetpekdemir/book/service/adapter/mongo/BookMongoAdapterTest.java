package com.mehmetpekdemir.book.service.adapter.mongo;

import com.mehmetpekdemir.book.service.adapter.mongo.document.BookDocument;
import com.mehmetpekdemir.book.service.adapter.mongo.repository.BookMongoRepository;
import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class BookMongoAdapterTest extends OrderTest {

    private BookMongoAdapter bookMongoAdapter;

    @Mock
    private BookMongoRepository bookMongoRepository;

    @BeforeEach
    void setUp() {
        bookMongoAdapter = new BookMongoAdapter(bookMongoRepository);
    }

    @Test
    void should_exists_book_by_isbn() {
        //given
        String isbn = "isbn";

        when(bookMongoRepository.findByIsbn(isbn))
                .thenReturn(Optional.of(new BookDocument()));

        //when
        boolean exists = bookMongoAdapter.existsBookByIsbn(isbn);

        //then
        assertThat(exists).isTrue();
        inOrder.verify(bookMongoRepository).findByIsbn(isbn);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_does_not_exists_book_by_isbn() {
        //given
        String isbn = "isbn";

        when(bookMongoRepository.findByIsbn(isbn))
                .thenReturn(Optional.empty());

        //when
        boolean doesNotExists = bookMongoAdapter.existsBookByIsbn(isbn);

        //then
        assertThat(doesNotExists).isFalse();
        inOrder.verify(bookMongoRepository).findByIsbn(isbn);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_exists_book_by_book_uid() {
        //given
        String bookUid = "bookUid";

        when(bookMongoRepository.findByUid(bookUid))
                .thenReturn(Optional.of(new BookDocument()));

        //when
        boolean exists = bookMongoAdapter.existsBookByBookUid(bookUid);

        //then
        assertThat(exists).isTrue();
        inOrder.verify(bookMongoRepository).findByUid(bookUid);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_does_not_exists_book_by_bookUid() {
        //given
        String bookUid = "bookUid";

        when(bookMongoRepository.findByUid(bookUid))
                .thenReturn(Optional.empty());

        //when
        boolean doesNotExists = bookMongoAdapter.existsBookByBookUid(bookUid);

        //then
        assertThat(doesNotExists).isFalse();
        inOrder.verify(bookMongoRepository).findByUid(bookUid);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_create_book() {
        //given
        BookCommand command = BookCommand.builder()
                .isbn("isbn")
                .name("name")
                .description("description")
                .authorName("authorName")
                .build();

        //when
        bookMongoAdapter.createBook(command);

        //then
        inOrder.verify(bookMongoRepository).save(any());
        inOrder.verifyNoMoreInteractions();
    }
}