package com.mehmetpekdemir.customer.service.domain.customer.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCommand {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
