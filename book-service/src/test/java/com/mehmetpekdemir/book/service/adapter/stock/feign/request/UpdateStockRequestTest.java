package com.mehmetpekdemir.book.service.adapter.stock.feign.request;

import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.book.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateStockRequestTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        StockCommand command = StockCommand.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        //when
        UpdateStockRequest request = UpdateStockRequest.fromModel(command);

        //then
        assertThat(request.getBookUid()).isEqualTo("bookUid");
        assertThat(request.getAmount()).isEqualByComparingTo(1);
    }
}