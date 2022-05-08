package com.mehmetpekdemir.book.service.application.port;

import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;

public interface StockApiPort {

    void updateBookStock(StockCommand command);
}
