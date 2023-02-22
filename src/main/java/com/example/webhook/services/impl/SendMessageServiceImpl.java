package com.example.webhook.services.impl;

import com.example.webhook.model.ReceiveMessage;
import com.example.webhook.dao.TextMessage;
import com.example.webhook.repository.MessageRepository;
import com.example.webhook.services.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Value("${facebook.api.url}")
    private String facebookApiUrl;
    @Autowired
    private MessageRepository messageRepository;


    @Override
    public ReceiveMessage sendMessage(TextMessage message, String authorizationHeader,String mobileId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TextMessage> request = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = restTemplate.exchange(facebookApiUrl+mobileId+"/messages", HttpMethod.POST, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ReceiveMessage receiveMessage = new ReceiveMessage();
            receiveMessage.setFrom(mobileId);
            receiveMessage.setTo(message.getTo());
            receiveMessage.setText(message.getText().getBody());
            receiveMessage.setTimestamp( new Date(System.currentTimeMillis()));
            return messageRepository.save(receiveMessage);
        } else {
            throw new RuntimeException(response.getBody()) ;
        }

    }

    @Override
    public ReceiveMessage getMessage(String from) {
        ReceiveMessage receiveMessage = this.messageRepository.findByFrom(from);
        if(receiveMessage==null){
            throw new RuntimeException("no message to this number");
        }
        return receiveMessage;
    }
}
