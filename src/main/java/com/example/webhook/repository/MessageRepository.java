package com.example.webhook.repository;

import com.example.webhook.model.TemplateMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<TemplateMessage,String > {
}
