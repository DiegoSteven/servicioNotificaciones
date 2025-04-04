package com.example.services;

import org.springframework.stereotype.Service;

import com.example.models.*;

@Service
public class NotificationFormatter {

    public NotificationMessage format(NotificationModel notification, User user, Event event, Position position) {
        StringBuilder body = new StringBuilder();

        body.append("📢 Evento: ").append(event.getType());

        if (user.getEmail() != null) {
            body.append("\n👤 Usuario: ").append(user.getEmail());
        }

        if (position != null) {
            body.append("\n📍 Ubicación: ")
                .append(position.getLatitude())
                .append(", ")
                .append(position.getLongitude());
        }

        if (notification.getDescription() != null) {
            body.append("\n📝 Descripción: ").append(notification.getDescription());
        }

        return new NotificationMessage("Notificación: " + event.getType(), body.toString());
    }
}
