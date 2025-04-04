package com.example.models;

public class NotificationMessage {

    private final String subject;
    private final String body;

    public NotificationMessage(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
