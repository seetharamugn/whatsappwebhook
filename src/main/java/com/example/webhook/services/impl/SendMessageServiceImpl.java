package com.example.webhook.services.impl;

import com.example.webhook.model.TextMessage;
import com.example.webhook.services.SendMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Value("${facebook.api.url}")
    private String facebookApiUrl;

    @Override
    public ResponseEntity<String> sendMessage(TextMessage message, String authorizationHeader) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TextMessage> request = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = restTemplate.exchange(facebookApiUrl, HttpMethod.POST, request, String.class);
        System.out.println(response.getStatusCode());
        return response;
    }
}
