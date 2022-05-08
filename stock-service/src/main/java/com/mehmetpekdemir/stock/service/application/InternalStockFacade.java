package com.mehmetpekdemir.stock.service.application;

import com.mehmetpekdemir.stock.service.adapter.mongo.document.StockDocument;
import com.mehmetpekdemir.stock.service.application.port.StockPersistencePort;
import com.mehmetpekdemir.stock.service.domain.exception.BusinessException;
import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternalStockFacade {

    private final StockPersistencePort stockPersistencePort;

    public void updateBookStock(StockCommand command) {
        stockPersistencePort.updateBookStock(command);
    }

    public void findAndModify(String bookUid, Integer amount) {
        Integer calculatedAmount = process(bookUid, amount);
        stockPersistencePort.findAndModify(bookUid, calculatedAmount);
    }

    private Integer process(String bookUid, Integer amount) {
        StockDocument stockDocument = validate(bookUid, amount);
        return calculateAmount(stockDocument.getAmount(), amount);
    }

    private StockDocument validate(String bookUid, Integer amount) {
        StockDocument stockDocument = stockPersistencePort.retrieveByBookUid(bookUid)
                .orElseThrow(() -> new BusinessException("Book not found !"));

        if (stockDocument.getAmount() <= 0 || stockDocument.getAmount() < amount) {
            throw new BusinessException("Insufficient stock exception !");
        }

        return stockDocument;
    }

    private Integer calculateAmount(Integer stockAmount, Integer reserveAmount) {
        return stockAmount - reserveAmount;
    }
}
