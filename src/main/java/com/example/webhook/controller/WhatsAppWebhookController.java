package com.example.webhook.controller;

import com.example.webhook.model.FacebookMessage;
import com.example.webhook.model.FacebookMessageLanguage;
import com.example.webhook.model.FacebookMessageTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WhatsAppWebhookController {

    private static final String VERIFY_TOKEN = "1234";

    @Value("${facebook.page-access-token}")
    private String pageAccessToken;

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

    @GetMapping("/")
    public String hokme() {
       return "heelo";
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

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("messageProduct")String messageProduct, @RequestParam("type") String type, @RequestParam("name") String name,@RequestParam("code") String code) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String[] headers = csvReader.readNext(); // Read the CSV header
            Map<String, Integer> headerMap = createHeaderMap(headers);
            String columnName = "contact"; // Read the column named "Column B"
            Integer columnToRead = headerMap.get(columnName);
            if (columnToRead == null) {
                return new ResponseEntity<>("Column not found: " + columnName, HttpStatus.BAD_REQUEST);
            }
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                String columnValue = line[columnToRead];
                this.sendMessage1(columnValue,messageProduct,type,name,code);
                // Process the column value here
                System.out.println(columnValue);
            }
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException | CsvValidationException e) {
            return new ResponseEntity<>("Failed to upload the file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String, Integer> createHeaderMap(String[] headers) {
        Map<String, Integer> headerMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i], i);
        }
        return headerMap;
    }

    public ResponseEntity<String> sendMessage1( String phoneNumber,String messageProduct,String type,String name, String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(pageAccessToken);
        FacebookMessage message = new FacebookMessage();
        message.setMessaging_product(messageProduct);
        message.setTo(phoneNumber);
        message.setType(type);
        FacebookMessageTemplate template = new FacebookMessageTemplate();
        template.setName(name);
        FacebookMessageLanguage language = new FacebookMessageLanguage();
        language.setCode(code);
        template.setLanguage(language);
        message.setTemplate(template);
        HttpEntity<FacebookMessage> request = new HttpEntity<>(message, headers);
        String url = "https://graph.facebook.com/v15.0/107683368889264/messages";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response;
    }
    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody FacebookMessage message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(pageAccessToken);
        HttpEntity<FacebookMessage> request = new HttpEntity<>(message, headers);
        String url = "https://graph.facebook.com/v15.0/107683368889264/messages";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response;
    }

}
