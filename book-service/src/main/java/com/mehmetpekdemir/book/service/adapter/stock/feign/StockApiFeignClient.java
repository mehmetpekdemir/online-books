package com.mehmetpekdemir.book.service.adapter.stock.feign;

import com.mehmetpekdemir.book.service.adapter.stock.feign.request.UpdateStockRequest;
import com.mehmetpekdemir.book.service.infrastructure.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@FeignClient(name = "stock-service", url = "http://localhost:8504", configuration = FeignClientConfiguration.class)
public interface StockApiFeignClient {

    @PutMapping("/api/v1/internal/books/stock")
    void updateBookStock(@RequestBody @Valid UpdateStockRequest request);
}
