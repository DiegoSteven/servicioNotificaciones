package com.example.services.Notificators;

/* 
@Component
public class NotificatorTraccar implements Notificator {

    @Value("${traccar.push.url:https://www.traccar.org/push/}")
    private String pushUrl;

    @Value("${traccar.push.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final NotificationFormatter formatter;

    @Autowired
    public NotificatorTraccar(NotificationFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getType() {
        return "traccar";
    }

    @Override
    public void send(NotificationModel notification, User user, Event event, Position position) {
        if (!user.getAttributes().containsKey("notificationTokens")) {
            System.err.println("❌ Usuario sin tokens de notificación.");
            return;
        }

        NotificationMessage msg = formatter.format(notification, user, event, position);

        List<String> tokens = Arrays.asList(
            user.getAttributes().get("notificationTokens").toString().split("[, ]")
        );

        Map<String, Object> notification = new HashMap<>();
        notification.put("title", msg.getSubject());
        notification.put("body", msg.getBody());
        notification.put("sound", "default");

        Map<String, Object> body = new HashMap<>();
        body.put("registration_ids", tokens);
        body.put("notification", notification);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=" + apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(pushUrl, request, String.class);
        System.out.println("🚀 Notificación Traccar enviada (status: " + response.getStatusCode() + ")");
    }
}

*/