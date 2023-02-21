package com.example.webhook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;

@Document(collection = "text_messages")
public class TextMessageWithResponse {

    @Id
    private String id;
    private TextMessage inputMessage;

    private TemplateMessage templateMessage;
    private String apiResponse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public TextMessage getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(TextMessage inputMessage) {
        this.inputMessage = inputMessage;
    }

    public TemplateMessage getTemplateMessage() {
        return templateMessage;
    }

    public void setTemplateMessage(TemplateMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }
// getters and setters
}