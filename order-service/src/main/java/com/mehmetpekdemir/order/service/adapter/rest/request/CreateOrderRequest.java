package com.mehmetpekdemir.order.service.adapter.rest.request;

import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotBlank
    private String customerUid;

    @NotBlank
    private String bookUid;

    @NotNull
    @Positive
    private Integer amount;

    @NotNull
    @Positive
    private BigDecimal totalPrice;

    public OrderCommand toModel() {
        return OrderCommand.builder()
                .customerUid(customerUid)
                .bookUid(bookUid)
                .amount(amount)
                .totalPrice(totalPrice)
                .build();
    }
}
