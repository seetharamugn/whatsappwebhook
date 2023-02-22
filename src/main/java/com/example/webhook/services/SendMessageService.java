package com.example.webhook.services;

import com.example.webhook.dao.TextMessage;
import org.springframework.http.ResponseEntity;

public interface SendMessageService {
    ResponseEntity<String> sendMessage(TextMessage message, String authorizationHeader,String mobileId);
}
