package com.example.webhook.services;

import com.example.webhook.dao.TextMessage;
import com.example.webhook.model.ReceiveMessage;
import org.springframework.http.ResponseEntity;

public interface SendMessageService {
    ReceiveMessage sendMessage(TextMessage message, String authorizationHeader, String mobileId);

    ReceiveMessage getMessage(String from);
}
