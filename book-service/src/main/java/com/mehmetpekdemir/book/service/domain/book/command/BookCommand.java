package com.mehmetpekdemir.book.service.domain.book.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCommand {

    private String isbn;
    private String name;
    private String description;
    private String authorName;
}
