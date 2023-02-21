package com.example.webhook.services;

import com.example.webhook.model.TextMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface SendMessageService {
    ResponseEntity<String> sendMessage(TextMessage message, String authorizationHeader);
}
