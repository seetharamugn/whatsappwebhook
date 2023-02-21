package com.example.webhook.controller;

import com.example.webhook.model.*;
import com.example.webhook.services.BulkMessageService;
import com.example.webhook.services.SendMessageService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WhatsAppWebhookController {
    @Autowired
    private HttpServletRequest request;
    private final String facebookApiUrl = "https://graph.facebook.com/v15.0/107683368889264/messages";
    @Autowired
    private  BulkMessageService bulkMessageService;

    @Autowired
    private SendMessageService sendMessageService;


    @GetMapping("/")
    public String hokme() {
        return "welcome to whatsapp integration ";
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody TextMessage message, @RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<String> response = sendMessageService.sendMessage(message, authorizationHeader);
        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/send-bulk-Message")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
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
                bulkMessageService.sendBulkMessage(columnValue);
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
   /* @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody TextMessage message) {
        RestTemplate restTemplate = new RestTemplate();
        String authorizationHeader = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TextMessage> request1 = new HttpEntity<>(message, headers);

        ResponseEntity<String> response = restTemplate.exchange(facebookApiUrl, HttpMethod.POST, request1, String.class);
        return response;
    }*/


}
