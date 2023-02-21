package com.example.webhook.model;

public class TextMessage {

    /*private String id;*/
    private String messaging_product;

    private String recipient_type;
    private String to;
    private String type;

    private MessageText text;

    public TextMessage(/*String id,*/ String messaging_product, String recipient_type, String to, String type, MessageText text) {
        /*this.id = id;*/
        this.messaging_product = messaging_product;
        this.recipient_type = recipient_type;
        this.to = to;
        this.type = type;
        this.text = text;
    }

  /*  public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
*/
    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getRecipient_type() {
        return recipient_type;
    }

    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
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

    public MessageText getText() {
        return text;
    }

    public void setText(MessageText text) {
        this.text = text;
    }
}
