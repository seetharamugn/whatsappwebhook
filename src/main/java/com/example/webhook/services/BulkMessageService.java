package com.example.webhook.services;

import org.springframework.http.ResponseEntity;

public interface BulkMessageService {
    ResponseEntity<String> sendBulkMessage(String phoneNumber);
}
