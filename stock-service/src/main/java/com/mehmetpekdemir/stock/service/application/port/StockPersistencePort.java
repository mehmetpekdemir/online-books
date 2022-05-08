package com.mehmetpekdemir.stock.service.application.port;

import com.mehmetpekdemir.stock.service.adapter.mongo.document.StockDocument;
import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;

import java.util.Optional;

public interface StockPersistencePort {

    Optional<StockDocument> retrieveByBookUid(String bookUid);

    void updateBookStock(StockCommand command);

    void findAndModify(String bookUid, Integer amount);
}
