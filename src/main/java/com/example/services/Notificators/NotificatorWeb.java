package com.example.services.Notificators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.models.*;
import com.example.services.NotificationFormatter;

@Component
public class NotificatorWeb implements Notificator {

    private final NotificationFormatter formatter;

    @Autowired
    public NotificatorWeb(NotificationFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getType() {
        return "web";
    }

    @Override
    public void send(NotificationModel notification, User user, Event event, Position position) {
        NotificationMessage message = formatter.format(notification, user, event, position);

        // Simulación de envío por WebSocket o log
        System.out.println("📡 [WEB Notification]");
        System.out.println("Usuario: " + user.getEmail());
        System.out.println("Asunto: " + message.getSubject());
        System.out.println("Mensaje:\n" + message.getBody());
        System.out.println("-------------------------------");
    }
}