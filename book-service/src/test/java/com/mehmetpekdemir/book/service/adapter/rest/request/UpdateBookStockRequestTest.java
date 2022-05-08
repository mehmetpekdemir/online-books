package com.mehmetpekdemir.book.service.adapter.rest.request;

import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.book.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateBookStockRequestTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        UpdateBookStockRequest request = UpdateBookStockRequest.builder()
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