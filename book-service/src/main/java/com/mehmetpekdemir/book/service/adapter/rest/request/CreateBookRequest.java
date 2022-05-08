package com.mehmetpekdemir.book.service.adapter.rest.request;

import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank
    private String isbn;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String authorName;

    public BookCommand toModel() {
        return BookCommand.builder()
                .isbn(isbn)
                .name(name)
                .description(description)
                .authorName(authorName)
                .build();
    }
}
