package com.example.webhook.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("responses")
public class RestResponse {
    private String data;
    private String metadata;
    private Date timestamp;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}