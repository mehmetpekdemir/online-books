package com.mehmetpekdemir.book.service.adapter.stock.feign.request;

import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStockRequest {

    private String bookUid;
    private Integer amount;

    public static UpdateStockRequest fromModel(StockCommand command) {
        return UpdateStockRequest.builder()
                .bookUid(command.getBookUid())
                .amount(command.getAmount())
                .build();
    }
}
