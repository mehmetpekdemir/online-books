package com.mehmetpekdemir.order.service.application.port;

public interface StockApiPort {

    void findAndModify(String bookUid, Integer amount);
}
