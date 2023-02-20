package com.example.webhook.repository;

import com.example.webhook.model.FacebookMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<FacebookMessage,String > {
}
