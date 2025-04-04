package com.example.services.Notificators;

/* 
import org.springframework.stereotype.Component;

@Component
public class NotificatorPushover implements Notificator {

    @Value("${pushover.token}")
    private String appToken;

    @Value("${pushover.defaultUserKey}")
    private String defaultUserKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final NotificationFormatter formatter;

    @Autowired
    public NotificatorPushover(NotificationFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getType() {
        return "pushover";
    }

    @Override
    public void send(NotificationModel notification, User user, Event event, Position position) {
        NotificationMessage message = formatter.format(notification, user, event, position);

        String userKey = user.getAttributes().getOrDefault("pushoverUserKey", defaultUserKey).toString();
        String device = (String) user.getAttributes().get("pushoverDeviceNames"); // opcional

        String url = "https://api.pushover.net/1/messages.json";

        Map<String, String> payload = new HashMap<>();
        payload.put("token", appToken);
        payload.put("user", userKey);
        payload.put("title", message.getSubject());
        payload.put("message", message.getBody());

        if (device != null && !device.isBlank()) {
            payload.put("device", device.replaceAll(" *, *", ","));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("📲 Pushover enviado (status: " + response.getStatusCode() + ")");
    }
}
    */