package com.mehmetpekdemir.book.service.adapter.stock;

import com.mehmetpekdemir.book.service.adapter.stock.feign.StockApiFeignClient;
import com.mehmetpekdemir.book.service.adapter.stock.feign.request.UpdateStockRequest;
import com.mehmetpekdemir.book.service.application.port.StockApiPort;
import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockApiAdapter implements StockApiPort {

    private final StockApiFeignClient stockApiFeignClient;

    @Override
    public void updateBookStock(StockCommand command) {
        log.info("Update book stock for bookUid : {} , amount : {}", command.getBookUid(), command.getAmount());
        stockApiFeignClient.updateBookStock(UpdateStockRequest.fromModel(command));
    }
}
