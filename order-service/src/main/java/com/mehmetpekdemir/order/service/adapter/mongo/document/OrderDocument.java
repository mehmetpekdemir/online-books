package com.mehmetpekdemir.order.service.adapter.mongo.document;

import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import com.mehmetpekdemir.order.service.infrastructure.util.UidGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "order")
public class OrderDocument {

    @Id
    private String id;
    private String uid;
    private String customerUid;
    private String bookUid;
    private Integer amount;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Order toModel(OrderDocument orderDocument) {
        return Order.builder()
                .orderUid(orderDocument.getUid())
                .amount(orderDocument.getAmount())
                .totalPrice(orderDocument.getTotalPrice())
                .orderStatus(orderDocument.getStatus())
                .build();
    }

    public static OrderDocument fromModel(OrderCommand command) {
        OrderDocument orderDocument = new OrderDocument();
        orderDocument.setUid(UidGenerator.generateUid());
        orderDocument.setCustomerUid(command.getCustomerUid());
        orderDocument.setBookUid(command.getBookUid());
        orderDocument.setAmount(command.getAmount());
        orderDocument.setTotalPrice(command.getTotalPrice());
        return orderDocument;
    }
}
