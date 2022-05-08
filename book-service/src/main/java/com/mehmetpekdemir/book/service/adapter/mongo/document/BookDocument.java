package com.mehmetpekdemir.book.service.adapter.mongo.document;

import com.mehmetpekdemir.book.service.domain.book.command.BookCommand;
import com.mehmetpekdemir.book.service.domain.enumtype.BookStatus;
import com.mehmetpekdemir.book.service.infrastructure.util.UidGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "book")
public class BookDocument {

    @Id
    private String id;
    private String uid;
    private String isbn;
    private String name;
    private String description;
    private String authorName;
    private BookStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BookDocument fromModel(BookCommand command) {
        BookDocument bookDocument = new BookDocument();
        bookDocument.setUid(UidGenerator.generateUid());
        bookDocument.setIsbn(command.getIsbn());
        bookDocument.setName(command.getName());
        bookDocument.setDescription(command.getDescription());
        bookDocument.setAuthorName(command.getAuthorName());
        return bookDocument;
    }
}
