package com.example.webhook.model;

public class FacebookMessage {
    private String messaging_product;
    private String to;
    private String type;
    private FacebookMessageTemplate template;

    public FacebookMessage(String messaging_product, String to, String type, FacebookMessageTemplate template) {
        this.messaging_product = messaging_product;
        this.to = to;
        this.type = type;
        this.template = template;
    }

    public FacebookMessage() {

    }

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FacebookMessageTemplate getTemplate() {
        return template;
    }

    public void setTemplate(FacebookMessageTemplate template) {
        this.template = template;
    }

    // Constructor, getters and setters
}

