package com.example.webhook.repository;

import com.example.webhook.model.ReceiveMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<ReceiveMessage, String> {
    ReceiveMessage findByFrom(String from);
}