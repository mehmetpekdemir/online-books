package com.mehmetpekdemir.book.service.adapter.rest.request;

import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateBookRequestTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CreateBookRequest request = CreateBookRequest.builder()
                .isbn("isbn")
                .name("name")
                .description("description")
                .authorName("authorName")
                .build();

        //when
        BookCommand bookCommand = request.toModel();

        //then
        assertThat(bookCommand.getIsbn()).isEqualTo("isbn");
        assertThat(bookCommand.getName()).isEqualTo("name");
        assertThat(bookCommand.getDescription()).isEqualTo("description");
        assertThat(bookCommand.getAuthorName()).isEqualTo("authorName");
    }
}