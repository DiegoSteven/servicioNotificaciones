package com.example.models;

public class EventWrapper {
    private Event event;
    private Device device;
    private Position position;

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public Device getDevice() { return device; }
    public void setDevice(Device device) { this.device = device; }

    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
}
