package com.mehmetpekdemir.order.service.adapter.stock.feign;

import com.mehmetpekdemir.order.service.infrastructure.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name = "stock-service", url = "http://localhost:8504", configuration = FeignClientConfiguration.class)
public interface StockApiFeignClient {

    @PutMapping("/api/v1/internal/books/{bookUid}/amount/{amount}")
    void findAndModify(@PathVariable String bookUid, @PathVariable Integer amount);
}
