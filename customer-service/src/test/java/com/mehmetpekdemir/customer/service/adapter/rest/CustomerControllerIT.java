package com.mehmetpekdemir.customer.service.adapter.rest;

import com.mehmetpekdemir.customer.service.adapter.mongo.document.CustomerDocument;
import com.mehmetpekdemir.customer.service.adapter.mongo.repository.CustomerMongoRepository;
import com.mehmetpekdemir.customer.service.adapter.order.feign.response.OrderResponse;
import com.mehmetpekdemir.customer.service.adapter.rest.request.CreateCustomerRequest;
import com.mehmetpekdemir.customer.service.adapter.rest.response.RetrieveCustomerOrdersResponse;
import com.mehmetpekdemir.customer.service.domain.enumtype.CustomerStatus;
import com.mehmetpekdemir.customer.service.infrastructure.BaseIT;
import com.mehmetpekdemir.customer.service.infrastructure.util.UidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

class CustomerControllerIT extends BaseIT {

    @Autowired
    private CustomerMongoRepository customerMongoRepository;

    @Test
    void should_create_customer() {
        //given
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .firstName("Mehmet")
                .lastName("Pekdemir")
                .email(UidGenerator.generateUid() + "@hotmail.com")
                .password("password")
                .confirmPassword("password")
                .build();

        //when
        ResponseEntity<Void> result = testRestTemplate.postForEntity("/api/v1/customers", request, Void.class);

        //then
        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);

        Optional<CustomerDocument> customerDocumentOptional = customerMongoRepository.findByEmail("mehmetpekdemir06@hotmail.com");
        assertThat(customerDocumentOptional).isNotNull();

        CustomerDocument customerDocument = customerDocumentOptional.get();
        assertThat(customerDocument.getFirstName()).isEqualTo("Mehmet");
        assertThat(customerDocument.getLastName()).isEqualTo("Pekdemir");
        assertThat(customerDocument.getEmail()).isNotNull();
        assertThat(customerDocument.getPassword()).isEqualTo("password");
        assertThat(customerDocument.getStatus()).isEqualTo(CustomerStatus.ACTIVE);

        assertThat(customerDocument.getId()).isNotNull();
        assertThat(customerDocument.getUid()).isNotNull();
        assertThat(customerDocument.getCreatedAt()).isNotNull();
        assertThat(customerDocument.getUpdatedAt()).isNull();
    }

    @Test
    void should_retrieve_customer_orders() {
        //given
        String customerUid = "customerUid";
        PageRequest request = PageRequest.of(0, 10);

        OrderResponse orders = OrderResponse.builder().orders(null).build();

        when(orderApiFeignClient.retrieveCustomerOrders(customerUid, request)).thenReturn(orders);

        //when
        ResponseEntity<RetrieveCustomerOrdersResponse> result = testRestTemplate.getForEntity("/api/v1/customers/" + customerUid + "/orders?page=0&size=10", RetrieveCustomerOrdersResponse.class);

        //then
        assertThat(result.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
    }
}