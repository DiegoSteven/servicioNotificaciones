package com.example.models;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

import com.example.Util.AttributesConverter;

@Entity
@Table(name = "tc_notifications")
public class NotificationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Convert(converter = AttributesConverter.class)
    @Column(name = "attributes")
    private Map<String, Object> attributes = new HashMap<>();

    @Column(name = "description")
    private String description;

    @Column(name = "calendarid")
    private Long calendarId;

    @Column(name = "always")
    private boolean always;

    @Column(name = "type")
    private String type;

    @Column(name = "commandid")
    private Long commandId;

    @Column(name = "notificators")
    private String notificators;

    

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCalendarId() {
        return calendarId != null ? calendarId : 0L;
    }

    public void setCalendarId(Long calendarId) {
        this.calendarId = calendarId;
    }

    public boolean isAlways() {
        return always;
    }

    public void setAlways(boolean always) {
        this.always = always;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCommandId() {
        return commandId != null ? commandId : 0L;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }

    public String getNotificators() {
        return notificators;
    }

    public void setNotificators(String notificators) {
        this.notificators = notificators;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
