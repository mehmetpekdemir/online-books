package com.mehmetpekdemir.book.service.adapter.stock;

import com.mehmetpekdemir.book.service.adapter.stock.feign.StockApiFeignClient;
import com.mehmetpekdemir.book.service.domain.stock.command.StockCommand;
import com.mehmetpekdemir.book.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;

class StockApiAdapterTest extends OrderTest {

    private StockApiAdapter stockApiAdapter;

    @Mock
    private StockApiFeignClient stockApiFeignClient;

    @BeforeEach
    void setUp() {
        stockApiAdapter = new StockApiAdapter(stockApiFeignClient);
    }

    @Test
    void should_update_book_stock() {
        //given
        StockCommand command = StockCommand.builder().build();

        //when
        stockApiAdapter.updateBookStock(command);

        //then
        inOrder.verify(stockApiFeignClient).updateBookStock(any());
        inOrder.verifyNoMoreInteractions();
    }
}