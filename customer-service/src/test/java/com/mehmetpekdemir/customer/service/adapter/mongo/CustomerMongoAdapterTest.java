package com.mehmetpekdemir.customer.service.adapter.mongo;

import com.mehmetpekdemir.customer.service.adapter.mongo.document.CustomerDocument;
import com.mehmetpekdemir.customer.service.adapter.mongo.repository.CustomerMongoRepository;
import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.infrastructure.OrderTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerMongoAdapterTest extends OrderTest {

    private CustomerMongoAdapter customerMongoAdapter;

    @Mock
    private CustomerMongoRepository customerMongoRepository;

    @BeforeEach
    void setUp() {
        customerMongoAdapter = new CustomerMongoAdapter(customerMongoRepository);
    }

    @Test
    void should_exists_customer_is_present() {
        //given
        String email = "mehmetpekdemir06@hotmail.com";

        when(customerMongoRepository.findByEmail(email)).thenReturn(Optional.of(new CustomerDocument()));

        //when
        boolean present = customerMongoAdapter.existsCustomer(email);

        //then
        assertThat(present).isTrue();
    }

    @Test
    void should_exists_customer_is_empty() {
        //given
        String email = "mehmetpekdemir06@hotmail.com";

        when(customerMongoRepository.findByEmail(email)).thenReturn(Optional.empty());

        //when
        boolean empty = customerMongoAdapter.existsCustomer(email);

        //then
        assertThat(empty).isFalse();
    }

    @Test
    void should_create_customer() {
        //given
        CustomerCommand command = CustomerCommand.builder()
                .firstName("Mehmet")
                .lastName("Pekdemir")
                .email("mehmetpekdemir06@hotmail.com")
                .password("123456")
                .build();

        //when
        customerMongoAdapter.createCustomer(command);

        //then
        inOrder.verify(customerMongoRepository).save(any());
        inOrder.verifyNoMoreInteractions();
    }
}