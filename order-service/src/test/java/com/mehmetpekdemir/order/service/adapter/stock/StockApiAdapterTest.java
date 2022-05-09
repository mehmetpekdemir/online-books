package com.mehmetpekdemir.order.service.adapter.stock;

import com.mehmetpekdemir.order.service.adapter.stock.feign.StockApiFeignClient;
import com.mehmetpekdemir.order.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class StockApiAdapterTest extends OrderTest {

    private StockApiAdapter stockApiAdapter;

    @Mock
    private StockApiFeignClient stockApiFeignClient;

    @BeforeEach
    void setUp() {
        stockApiAdapter = new StockApiAdapter(stockApiFeignClient);
    }

    @Test
    void should_find_and_modify() {
        //given
        String bookUid = "bookUid";
        Integer amount = 3;

        //when
        stockApiAdapter.findAndModify(bookUid, amount);

        //then
        inOrder.verify(stockApiFeignClient).findAndModify(bookUid, amount);
        inOrder.verifyNoMoreInteractions();
    }
}