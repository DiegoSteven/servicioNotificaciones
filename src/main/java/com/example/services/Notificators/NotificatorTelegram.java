package com.example.services.Notificators;
/* 
import org.springframework.stereotype.Component;

@Component
public class NotificatorTelegram implements Notificator {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String defaultChatId;

    private final RestTemplate restTemplate = new RestTemplate();
    private final NotificationFormatter formatter;

    @Autowired
    public NotificatorTelegram(NotificationFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getType() {
        return "telegram";
    }

    @Override
    public void send(NotificationModel notification, User user, Event event, Position position) {
        NotificationMessage message = formatter.format(notification, user, event, position);

        String chatId = defaultChatId;

        if (user.getAttributes().containsKey("telegramChatId")) {
            chatId = user.getAttributes().get("telegramChatId").toString();
        }

        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        Map<String, String> body = new HashMap<>();
        body.put("chat_id", chatId);
        body.put("text", message.getBody());

        restTemplate.postForEntity(url, body, String.class);

        System.out.println("✅ Mensaje de Telegram enviado a chatId: " + chatId);
    }
}

*/