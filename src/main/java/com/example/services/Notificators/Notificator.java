package com.example.services.Notificators;

import com.example.models.*;
public interface Notificator {

    String getType();  // ej: "web", "mail", "telegram"

    void send(NotificationModel notification, User user, Event event, Position position);

    default void send(User user, NotificationMessage message, Event event, Position position) {
        throw new UnsupportedOperationException("Método no implementado en este notificator");
    }
}