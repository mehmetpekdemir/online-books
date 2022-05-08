package com.mehmetpekdemir.customer.service.adapter.rest.request;

import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.infrastructure.validator.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword", message = "{password.field.match.message}")
public class CreateCustomerRequest {

    @Email
    @NotBlank
    @Size(min = 1, max = 200)
    private String email;

    @NotBlank
    @Size(min = 2, max = 50)
    private String password;

    @NotBlank
    @Size(min = 2, max = 50)
    private String confirmPassword;

    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 50)
    private String lastName;

    public CustomerCommand toModel() {
        return CustomerCommand.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
