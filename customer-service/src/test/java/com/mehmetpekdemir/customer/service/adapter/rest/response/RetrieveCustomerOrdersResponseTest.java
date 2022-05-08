package com.mehmetpekdemir.customer.service.adapter.rest.response;

import com.mehmetpekdemir.customer.service.infrastructure.BaseTest;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class RetrieveCustomerOrdersResponseTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        Page<Order> orders = mock(Page.class);

        //when
        RetrieveCustomerOrdersResponse retrieveCustomerOrdersResponse = RetrieveCustomerOrdersResponse.fromModel(orders);

        //then
        assertThat(retrieveCustomerOrdersResponse).isNotNull();
    }
}