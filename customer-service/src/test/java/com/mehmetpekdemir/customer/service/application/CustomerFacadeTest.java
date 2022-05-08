package com.mehmetpekdemir.customer.service.application;

import com.mehmetpekdemir.customer.service.application.port.CustomerPersistencePort;
import com.mehmetpekdemir.customer.service.application.port.OrderApiPort;
import com.mehmetpekdemir.customer.service.infrastructure.OrderTest;
import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.domain.exception.BusinessException;
import com.mehmetpekdemir.customer.service.domain.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

class CustomerFacadeTest extends OrderTest {

    private CustomerFacade customerFacade;

    @Mock
    private CustomerPersistencePort customerPersistencePort;

    @Mock
    private OrderApiPort orderApiPort;

    @BeforeEach
    void setUp() {
        customerFacade = new CustomerFacade(customerPersistencePort, orderApiPort);
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

        when(customerPersistencePort.existsCustomer(command.getEmail())).thenReturn(false);

        //when
        customerFacade.createCustomer(command);

        //then
        inOrder.verify(customerPersistencePort).existsCustomer(command.getEmail());
        inOrder.verify(customerPersistencePort).createCustomer(command);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void should_create_customer_throw_business_exception() {
        //given
        CustomerCommand command = CustomerCommand.builder()
                .firstName("Mehmet")
                .lastName("Pekdemir")
                .email("mehmetpekdemir06@hotmail.com")
                .password("123456")
                .build();

        when(customerPersistencePort.existsCustomer(command.getEmail())).thenReturn(true);

        //when
        Throwable throwable = catchThrowable(() -> customerFacade.createCustomer(command));

        //then
        assertThat(throwable).isInstanceOf(BusinessException.class)
                .hasMessageContaining("Email already exists");
    }

    @Test
    void should_retrieve_customer_orders() {
        //given
        String customerUid = "customerUid";
        PageRequest pageRequest = PageRequest.of(0, 100);

        when(orderApiPort.retrieveCustomerOrders(customerUid, pageRequest)).thenReturn(Page.empty());

        //when
        Page<Order> orders = customerFacade.retrieveCustomerOrders(customerUid, pageRequest);

        //then
        inOrder.verify(orderApiPort).retrieveCustomerOrders(customerUid, pageRequest);
        inOrder.verifyNoMoreInteractions();
        assertThat(orders).isEmpty();
    }
}