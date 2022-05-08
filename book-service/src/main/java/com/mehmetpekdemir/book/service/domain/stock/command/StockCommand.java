package com.mehmetpekdemir.book.service.domain.stock.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockCommand {

    private String bookUid;
    private Integer amount;
}
