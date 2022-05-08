package com.mehmetpekdemir.book.service.adapter.rest;

import com.mehmetpekdemir.book.service.adapter.rest.request.CreateBookRequest;
import com.mehmetpekdemir.book.service.adapter.rest.request.UpdateBookStockRequest;
import com.mehmetpekdemir.book.service.application.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookFacade bookFacade;

    @PostMapping("/v1/books")
    public void createBook(@RequestBody @Valid CreateBookRequest request) {
        bookFacade.createBook(request.toModel());
    }

    @PutMapping("/v1/books/stock")
    public void updateBookStock(@RequestBody @Valid UpdateBookStockRequest request) {
        bookFacade.updateBookStock(request.toModel());
    }
}
