package com.mehmetpekdemir.stock.service.adapter.rest.internal.request;

import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.stock.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateStockRequestTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        UpdateStockRequest request = UpdateStockRequest.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        //when
        StockCommand stockCommand = request.toModel();

        //then
        assertThat(stockCommand.getBookUid()).isEqualTo("bookUid");
        assertThat(stockCommand.getAmount()).isEqualByComparingTo(1);
    }
}