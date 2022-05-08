package com.mehmetpekdemir.order.service.application;

import com.mehmetpekdemir.order.service.application.port.OrderPersistencePort;
import com.mehmetpekdemir.order.service.application.port.StockApiPort;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderPersistencePort orderPersistencePort;
    private final StockApiPort stockApiPort;

    @Transactional
    public void placeOrder(OrderCommand command) {
        findAndModify(command.getBookUid(), command.getAmount());
        place(command);
    }

    private void findAndModify(String bookUid, Integer amount) {
        stockApiPort.findAndModify(bookUid, amount); //like select for update
    }

    private void place(OrderCommand command) {
        orderPersistencePort.placeOrder(command);
    }

    public Order retrieveOrder(String orderUid) {
        return orderPersistencePort.retrieveOrder(orderUid);
    }

    public List<Order> retrieveOrders(LocalDateTime startDate, LocalDateTime endDate) {
        return orderPersistencePort.retrieveOrders(startDate, endDate);
    }
}
