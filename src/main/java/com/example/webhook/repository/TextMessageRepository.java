package com.example.webhook.repository;

import com.example.webhook.model.TextMessageWithResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TextMessageRepository extends MongoRepository<TextMessageWithResponse,String> {
}
