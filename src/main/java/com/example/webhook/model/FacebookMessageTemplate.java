package com.example.webhook.model;

public class FacebookMessageTemplate {
    private String name;
    private FacebookMessageLanguage language;

    public FacebookMessageTemplate(String name, FacebookMessageLanguage language) {
        this.name = name;
        this.language = language;
    }

    public FacebookMessageTemplate() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FacebookMessageLanguage getLanguage() {
        return language;
    }

    public void setLanguage(FacebookMessageLanguage language) {
        this.language = language;
    }
}

