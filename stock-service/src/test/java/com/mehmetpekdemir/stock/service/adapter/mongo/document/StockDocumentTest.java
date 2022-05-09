package com.mehmetpekdemir.stock.service.adapter.mongo.document;

import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.stock.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StockDocumentTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        StockCommand command = StockCommand.builder()
                .bookUid("bookUid")
                .amount(1)
                .build();

        //when
        StockDocument response = StockDocument.fromModel(command);

        //then
        assertThat(response.getUid()).isNotNull();
        assertThat(response.getBookUid()).isEqualTo("bookUid");
        assertThat(response.getAmount()).isEqualByComparingTo(1);
    }
}