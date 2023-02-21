package com.example.webhook.services;

import com.example.webhook.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveRestResponse(RestResponse response) {
        mongoTemplate.save(response);
    }
}