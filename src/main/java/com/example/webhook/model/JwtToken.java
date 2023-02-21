package com.example.webhook.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "jwt_tokens")
public class JwtToken {

    @Id
    private String id;

    @Field(name = "token")
    private String token;

    @Field(name = "issue_date")
    private Instant issueDate;
    @Field(name = "expiry_date")
    private Instant expiryDate;

    @Field(name = "user_id")
    private String userId;

    // constructors, getters and setters


    public JwtToken(String id, String token,Instant issueDate, Instant expiryDate, String userId) {
        this.id = id;
        this.token = token;
        this.issueDate=issueDate;
        this.expiryDate = expiryDate;
        this.userId = userId;
    }

    public JwtToken() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}