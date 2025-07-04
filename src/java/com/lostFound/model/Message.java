package com.lostfound.model;


import java.util.Date;


public class Message {
    private int senderId;
    private int receiverId;
    private String messageContent;
    private int itemId;
    private String itemType;
    private Date timestamp;

    // Getters and Setters
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Extra helper methods (aliases)
    public void setMessage(String message) {
        this.messageContent = message;
    }

    public String getMessage() {
        return this.messageContent;
    }

    public void setContent(String content) {
        this.messageContent = content;
    }

    public String getContent() {
        return this.messageContent;
    }
}
