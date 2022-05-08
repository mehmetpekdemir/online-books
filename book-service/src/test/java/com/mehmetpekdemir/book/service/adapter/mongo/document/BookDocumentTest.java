package com.mehmetpekdemir.book.service.adapter.mongo.document;

import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BookDocumentTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        BookCommand command = BookCommand.builder()
                .isbn("isbn")
                .name("name")
                .description("description")
                .authorName("authorName")
                .build();

        //when
        BookDocument bookDocument = BookDocument.fromModel(command);

        //then
        assertThat(bookDocument.getUid()).isNotNull();
        assertThat(bookDocument.getIsbn()).isEqualTo("isbn");
        assertThat(bookDocument.getName()).isEqualTo("name");
        assertThat(bookDocument.getDescription()).isEqualTo("description");
        assertThat(bookDocument.getAuthorName()).isEqualTo("authorName");
    }
}