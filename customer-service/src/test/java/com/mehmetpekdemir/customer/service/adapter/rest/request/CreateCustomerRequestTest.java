package com.mehmetpekdemir.customer.service.adapter.rest.request;

import com.mehmetpekdemir.customer.service.infrastructure.BaseTest;
import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreateCustomerRequestTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .firstName("Mehmet")
                .lastName("Pekdemir")
                .email("mehmetpekdemir06@hotmail.com")
                .password("password")
                .confirmPassword("password")
                .build();

        //when
        CustomerCommand command = request.toModel();

        //then
        assertThat(command.getFirstName()).isEqualTo("Mehmet");
        assertThat(command.getLastName()).isEqualTo("Pekdemir");
        assertThat(command.getEmail()).isEqualTo("mehmetpekdemir06@hotmail.com");
        assertThat(command.getPassword()).isEqualTo("password");
    }
}