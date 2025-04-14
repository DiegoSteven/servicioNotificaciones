package  com.example.Util;

import  com.example.models.Device;
import  com.example.models.Event;
import  com.example.models.Position;

public class NotificationFormatter {

    public static String formatMessage(Event event, Device device, Position position) {
        StringBuilder sb = new StringBuilder();

        sb.append("🔔 Evento: ").append(event.getType()).append("\n");
        sb.append("📡 Dispositivo: ").append(device != null ? device.getName() : event.getDeviceId()).append("\n");

        if (position != null) {
            sb.append("📍 Ubicación: ")
              .append("Lat: ").append(position.getLatitude())
              .append(", Lon: ").append(position.getLongitude()).append("\n");
            sb.append("⏰ Fecha/hora: ").append(position.getFixTime()).append("\n");
        } else {
            sb.append("⏰ Fecha/hora del evento: ").append(event.getEventTime()).append("\n");
        }

        return sb.toString();
    }
}
