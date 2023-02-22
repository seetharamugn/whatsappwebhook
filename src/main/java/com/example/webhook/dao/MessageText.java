package com.example.webhook.dao;

public class MessageText {

    private boolean preview_url;
    private String body;

    public MessageText(boolean preview_url, String body) {
        this.preview_url = preview_url;
        this.body = body;
    }

    public boolean isPreview_url() {
        return preview_url;
    }

    public void setPreview_url(boolean preview_url) {
        this.preview_url = preview_url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
