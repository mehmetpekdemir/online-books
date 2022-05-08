package com.mehmetpekdemir.customer.service.adapter.mongo.document;

import com.mehmetpekdemir.customer.service.domain.customer.command.CustomerCommand;
import com.mehmetpekdemir.customer.service.domain.enumtype.CustomerStatus;
import com.mehmetpekdemir.customer.service.infrastructure.util.UidGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "customer")
public class CustomerDocument {

    @Id
    private String id;
    private String uid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private CustomerStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CustomerDocument fromModel(CustomerCommand command) {
        CustomerDocument customerDocument = new CustomerDocument();
        customerDocument.setUid(UidGenerator.generateUid());
        customerDocument.setEmail(command.getEmail());
        customerDocument.setPassword(command.getPassword());
        customerDocument.setFirstName(command.getFirstName());
        customerDocument.setLastName(command.getLastName());
        return customerDocument;
    }
}
