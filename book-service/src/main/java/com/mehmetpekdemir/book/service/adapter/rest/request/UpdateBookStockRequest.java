package com.mehmetpekdemir.book.service.adapter.rest.request;

import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookStockRequest {

    @NotBlank
    private String bookUid;

    @NotNull
    @Positive
    private Integer amount;

    public StockCommand toModel() {
        return StockCommand.builder()
                .bookUid(bookUid)
                .amount(amount)
                .build();
    }
}
