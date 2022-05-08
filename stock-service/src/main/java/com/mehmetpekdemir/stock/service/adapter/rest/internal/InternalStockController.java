package com.mehmetpekdemir.stock.service.adapter.rest.internal;

import com.mehmetpekdemir.stock.service.adapter.rest.internal.request.UpdateStockRequest;
import com.mehmetpekdemir.stock.service.application.InternalStockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InternalStockController {

    private final InternalStockFacade internalStockFacade;

    @PutMapping("/v1/internal/books/stock")
    public void updateBookStock(@RequestBody @Valid UpdateStockRequest request) {
        internalStockFacade.updateBookStock(request.toModel());
    }

    @PutMapping("/v1/internal/books/{bookUid}/amount/{amount}")
    public void findAndModify(@PathVariable String bookUid, @PathVariable Integer amount) {
        internalStockFacade.findAndModify(bookUid, amount);
    }
}
