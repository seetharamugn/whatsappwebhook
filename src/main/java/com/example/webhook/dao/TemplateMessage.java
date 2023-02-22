package com.example.webhook.dao;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Message")
public class TemplateMessage {

 /*   @Id
    private String id;*/
    private String messaging_product;
    private String to;
    private String type;
    private MessageTemplate template;

    public TemplateMessage( /*String id*/ String messaging_product, String to, String type, MessageTemplate template) {
        /*this.id=id;*/
        this.messaging_product = messaging_product;
        this.to = to;
        this.type = type;
        this.template = template;
    }

    public TemplateMessage() {

    }

/*    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

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

    public MessageTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MessageTemplate template) {
        this.template = template;
    }

    // Constructor, getters and setters
}

