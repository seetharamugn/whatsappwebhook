package com.example.webhook.services.impl;

import com.example.webhook.dao.MessageLanguage;
import com.example.webhook.dao.MessageTemplate;
import com.example.webhook.model.ReceiveMessage;
import com.example.webhook.dao.TemplateMessage;
import com.example.webhook.repository.MessageRepository;
import com.example.webhook.services.BulkMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Service
public class BulkMessageServiceImpl implements BulkMessageService {
    @Value("${facebook.api.url}")
    private String facebookApiUrl;

    @Value("${facebook.api.messaging_product}")
    private String messaging_product;

    @Value("${facebook.api.type}")
    private String type;

    @Value("${facebook.api.code}")
    private String code;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public ResponseEntity<String> sendBulkMessage(String phoneNumber,String mobileId) {
        String authorizationHeader = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        TemplateMessage message = new TemplateMessage();
        message.setMessaging_product(messaging_product);
        message.setTo(phoneNumber);
        message.setType(type);
        MessageTemplate template = new MessageTemplate();
        template.setName("hello_world");
        MessageLanguage language = new MessageLanguage();
        language.setCode(code);
        template.setLanguage(language);
        message.setTemplate(template);
        HttpEntity<TemplateMessage> req = new HttpEntity<>(message, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(facebookApiUrl+mobileId+"/messages", HttpMethod.POST, req, String.class);

        ReceiveMessage receiveMessage = new ReceiveMessage();
        receiveMessage.setFrom(mobileId);
        receiveMessage.setTo(message.getTo());
        receiveMessage.setText(message.getTemplate().getName());
        receiveMessage.setTimestamp( new Date(System.currentTimeMillis()));
        messageRepository.save(receiveMessage);
        return response;
    }
}
