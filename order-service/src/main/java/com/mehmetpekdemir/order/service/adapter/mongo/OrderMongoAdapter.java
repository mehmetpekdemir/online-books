package com.mehmetpekdemir.order.service.adapter.mongo;

import com.mehmetpekdemir.order.service.adapter.mongo.document.OrderDocument;
import com.mehmetpekdemir.order.service.adapter.mongo.repository.OrderMongoRepository;
import com.mehmetpekdemir.order.service.application.port.OrderPersistencePort;
import com.mehmetpekdemir.order.service.domain.enumtype.OrderStatus;
import com.mehmetpekdemir.order.service.domain.exception.BusinessException;
import com.mehmetpekdemir.order.service.domain.order.Order;
import com.mehmetpekdemir.order.service.domain.order.command.OrderCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderMongoAdapter implements OrderPersistencePort {

    private final OrderMongoRepository orderMongoRepository;

    @Override
    public Page<Order> retrieveCustomerOrders(String customerUid, Pageable pageable) {
        log.info("Retrieve customer orders by uid : {}", customerUid);
        return orderMongoRepository.findAllByCustomerUidOrderByCreatedAtDesc(customerUid, pageable)
                .map(OrderDocument::toModel);
    }

    @Override
    public void placeOrder(OrderCommand command) {
        log.info("Started place order for customerUid: {} ", command.getCustomerUid());
        OrderDocument orderDocument = OrderDocument.fromModel(command);
        orderDocument.setCreatedAt(LocalDateTime.now());
        orderDocument.setStatus(OrderStatus.COMPLETED);
        orderMongoRepository.save(orderDocument);
    }

    @Override
    public Order retrieveOrder(String orderUid) {
        log.info("Retrieve order by order uid : {}", orderUid);
        return orderMongoRepository.findByUid(orderUid)
                .map(OrderDocument::toModel)
                .orElseThrow(() -> new BusinessException("Order Not Found !"));
    }

    @Override
    public List<Order> retrieveOrders(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Retrieve orders from : {} to : {}", startDate, endDate);
        return orderMongoRepository.findAllByCreatedAtBetween(startDate, endDate).stream()
                .map(OrderDocument::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> retrieveCustomerOrderStatistics(String customerUid, LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Retrieve customer order statistics for customerUid : {} , startDate : {}, endDate : {}", customerUid, startDate, endDate);
        return orderMongoRepository
                .findOrderDocumentByCustomerUidAndCreatedAtBetween(customerUid, startDate, endDate).stream()
                .map(OrderDocument::toModel)
                .collect(Collectors.toList());
    }
}
