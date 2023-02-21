package com.example.webhook.controller;

import com.example.webhook.model.ReceiveMessage;
import com.example.webhook.model.TextMessageWithResponse;
import com.example.webhook.repository.MessageRepository;
import com.example.webhook.repository.TextMessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
public class WebhookController {

    @Value("${webhook.token.secret}")
    private String VERIFY_TOKEN;


    @Autowired
    private MessageRepository messageRepository;
    @GetMapping("/webhook")
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.verify_token") String token,
                                                @RequestParam("hub.challenge") String challenge) {
        if (mode.equals("subscribe") && token.equals(VERIFY_TOKEN)) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<Void> handleIncomingMessage(@RequestBody String messageBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(messageBody);
        System.out.println( root );
        JsonNode entry = root.get("entry").get(0);
        JsonNode messageNode = entry.get("changes").get(0).get("value").get("messages").get(0);

        ReceiveMessage message = new ReceiveMessage();
        message.setFrom(messageNode.get("from").asText());
        message.setTo(entry.get("id").asText());
        message.setText(messageNode.get("text").get("body").asText());
        message.setTimestamp(new Date(Long.parseLong(messageNode.get("timestamp").asText()) * 1000));

        messageRepository.save(message);


        return ResponseEntity.ok().build();
    }

}
