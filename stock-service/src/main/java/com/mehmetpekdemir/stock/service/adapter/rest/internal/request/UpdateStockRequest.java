package com.mehmetpekdemir.stock.service.adapter.rest.internal.request;

import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequest {

    private String bookUid;
    private Integer amount;

    public StockCommand toModel() {
        return StockCommand.builder()
                .bookUid(bookUid)
                .amount(amount)
                .build();
    }
}
