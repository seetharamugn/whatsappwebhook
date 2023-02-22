package com.example.webhook.services;

import com.example.webhook.model.ReceiveMessage;
import org.springframework.http.ResponseEntity;

public interface BulkMessageService {
    ReceiveMessage sendBulkMessage(String phoneNumber, String mobileId);
}
