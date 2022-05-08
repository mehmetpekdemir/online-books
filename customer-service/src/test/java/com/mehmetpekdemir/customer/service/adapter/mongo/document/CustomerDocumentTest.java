package com.mehmetpekdemir.customer.service.adapter.mongo.document;

import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.infrastructure.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomerDocumentTest extends BaseTest {

    @Test
    void should_convert() {
        //given
        CustomerCommand command = CustomerCommand.builder()
                .firstName("Mehmet")
                .lastName("Pekdemir")
                .email("mehmetpekdemir06@hotmail.com")
                .password("123456")
                .build();

        //when
        CustomerDocument customerDocument = CustomerDocument.fromModel(command);

        //then
        assertThat(customerDocument.getUid()).isNotNull();
        assertThat(customerDocument.getFirstName()).isEqualTo("Mehmet");
        assertThat(customerDocument.getLastName()).isEqualTo("Pekdemir");
        assertThat(customerDocument.getEmail()).isEqualTo("mehmetpekdemir06@hotmail.com");
        assertThat(customerDocument.getPassword()).isEqualTo("123456");
    }
}