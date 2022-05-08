package com.mehmetpekdemir.order.service.adapter.stock;

import com.mehmetpekdemir.order.service.adapter.stock.feign.StockApiFeignClient;
import com.mehmetpekdemir.order.service.application.port.StockApiPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockApiAdapter implements StockApiPort {

    private final StockApiFeignClient stockApiFeignClient;

    @Override
    public void findAndModify(String bookUid, Integer amount) {
        log.info("Find and modify for bookUid :{} , amount : {}", bookUid, amount);
        stockApiFeignClient.findAndModify(bookUid, amount);
    }
}
