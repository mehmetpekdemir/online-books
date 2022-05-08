package com.mehmetpekdemir.stock.service.adapter.mongo.document;

import com.mehmetpekdemir.stock.service.domain.enumtype.StockStatus;
import com.mehmetpekdemir.stock.service.domain.stock.Stock;
import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.stock.service.infrastructure.util.UidGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "stock")
public class StockDocument {

    @Id
    private String id;
    private String uid;
    private String bookUid;
    private Integer amount;
    private StockStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static StockDocument fromModel(StockCommand command) {
        StockDocument stockDocument = new StockDocument();
        stockDocument.setUid(UidGenerator.generateUid());
        stockDocument.setBookUid(command.getBookUid());
        stockDocument.setAmount(command.getAmount());
        return stockDocument;
    }

    public Stock toModel() {
        return Stock.builder()
                .stockUid(uid)
                .bookUid(bookUid)
                .amount(amount)
                .build();
    }
}
