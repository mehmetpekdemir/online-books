package com.mehmetpekdemir.stock.service.adapter.mongo;

import com.mehmetpekdemir.stock.service.adapter.mongo.document.StockDocument;
import com.mehmetpekdemir.stock.service.adapter.mongo.repository.StockMongoRepository;
import com.mehmetpekdemir.stock.service.application.port.StockPersistencePort;
import com.mehmetpekdemir.stock.service.domain.enumtype.StockStatus;
import com.mehmetpekdemir.stock.service.domain.stock.command.StockCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockMongoAdapter implements StockPersistencePort {

    private final StockMongoRepository stockMongoRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public void updateBookStock(StockCommand command) {
        retrieveByBookUid(command.getBookUid())
                .ifPresentOrElse(
                        stockDocument -> update(stockDocument, command),
                        () -> create(command)
                );
    }

    @Override
    public Optional<StockDocument> retrieveByBookUid(String bookUid) {
        log.info("Retrieve stock by bookUid {}", bookUid);
        return stockMongoRepository.findByBookUid(bookUid);
    }

    private void update(StockDocument stockDocument, StockCommand command) {
        log.info("Update book stock for bookUid : {} , amount : {}", command.getBookUid(), command.getAmount());
        stockDocument.setAmount(command.getAmount());
        stockDocument.setUpdatedAt(LocalDateTime.now());
        stockMongoRepository.save(stockDocument);
    }

    private void create(StockCommand command) {
        log.info("Create book stock for bookUid : {} , amount : {}", command.getBookUid(), command.getAmount());
        StockDocument stockDocument = StockDocument.fromModel(command);
        stockDocument.setStatus(StockStatus.ACTIVE);
        stockDocument.setCreatedAt(LocalDateTime.now());
        stockMongoRepository.save(stockDocument);
    }

    @Override
    public void findAndModify(String bookUid, Integer amount) {
        log.info("Find and modify for bookUid : {}, amount : {}", bookUid, amount);
        Query query = new Query();
        query.addCriteria(Criteria.where("bookUid").is(bookUid));
        Update update = new Update();
        update.set("amount", amount);

        mongoTemplate.findAndModify(query, update, StockDocument.class);
    }
}
