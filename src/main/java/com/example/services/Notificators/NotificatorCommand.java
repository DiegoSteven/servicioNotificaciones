package com.example.services.Notificators;

import org.springframework.stereotype.Component;
import com.example.models.*;
@Component
public class NotificatorCommand implements Notificator {

    @Override
    public String getType() {
        return "command";
    }

    @Override
    public void send(NotificationModel notification, User user, Event event, Position position) {
        if (notification.getCommandId() <= 0) {
            System.err.println("❌ commandId no definido. No se ejecuta ningún comando.");
            return;
        }

        // Simulación de ejecución de comando
        System.out.println("⚙️ Ejecutando comando con ID: " + notification.getCommandId() +
                " para el dispositivo ID: " + event.getDeviceId());

        // Aquí podrías integrar un CommandService que envíe el comando real.
        // Por ejemplo: commandService.sendCommand(notification.getCommandId(), event.getDeviceId());
    }
}