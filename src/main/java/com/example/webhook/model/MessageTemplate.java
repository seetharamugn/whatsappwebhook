package com.example.webhook.model;

public class MessageTemplate {
    private String name;
    private MessageLanguage language;

    public MessageTemplate(String name, MessageLanguage language) {
        this.name = name;
        this.language = language;
    }

    public MessageTemplate() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MessageLanguage getLanguage() {
        return language;
    }

    public void setLanguage(MessageLanguage language) {
        this.language = language;
    }
}

