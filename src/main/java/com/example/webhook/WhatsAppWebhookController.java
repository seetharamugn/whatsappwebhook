package com.example.webhook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WhatsAppWebhookController {

        private static final String VERIFY_TOKEN = "1234";

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
        // process the incoming message
        // ...

        return ResponseEntity.ok().build();
    }
}
