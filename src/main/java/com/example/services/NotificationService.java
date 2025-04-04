package com.example.services;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Dto.Typed;
import com.example.models.*;

import com.example.repositories.NotificationRepository;
import com.example.repositories.UserRepository;
import java.lang.reflect.Field;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificatorManager notificatorManager;

    @Autowired
    private NotificationFormatter formatter;

    @PersistenceContext
    private EntityManager entityManager;

    public List<NotificationModel> getAll() {
        return notificationRepository.findAll();
    }

    @Transactional
    public NotificationModel create(NotificationModel n, Long userId) {
        if (n.getCalendarId() != null && n.getCalendarId() == 0)
            n.setCalendarId(null);
        if (n.getCommandId() != null && n.getCommandId() == 0)
            n.setCommandId(null);

        NotificationModel saved = notificationRepository.save(n);

        entityManager.createNativeQuery(
                "INSERT INTO tc_user_notification (userid, notificationid) VALUES (:userId, :notificationId)")
                .setParameter("userId", userId)
                .setParameter("notificationId", saved.getId())
                .executeUpdate();

        return saved;
    }

    @Transactional
    public NotificationModel update(Long id, NotificationModel updated, Long userId) {
        if (notificationRepository.isLinkedToUser(userId, id) != 1) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No autorizado");
        }
        

        return notificationRepository.findById(id).map(existing -> {
            existing.setDescription(updated.getDescription());
            existing.setType(updated.getType());
            existing.setCommandId(
                    updated.getCommandId() != null && updated.getCommandId() == 0 ? null : updated.getCommandId());
            existing.setCalendarId(
                    updated.getCalendarId() != null && updated.getCalendarId() == 0 ? null : updated.getCalendarId());
            existing.setAlways(updated.isAlways());
            existing.setNotificators(updated.getNotificators());
            return notificationRepository.save(existing);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificación no encontrada"));
    }

    @Transactional
    public void delete(Long notificationId, Long userId) {
        int count = notificationRepository.countByUserAndNotification(userId, notificationId);

        if (count == 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "No tienes permiso para eliminar esta notificación");
        }

        // Eliminar primero el vínculo (IMPORTANTE: debe coincidir con los datos)
        entityManager.createNativeQuery("""
                    DELETE FROM tc_user_notification
                    WHERE userid = :userId AND notificationid = :notificationId
                """)
                .setParameter("userId", userId)
                .setParameter("notificationId", notificationId)
                .executeUpdate();

        // Luego eliminamos la notificación en sí
        notificationRepository.deleteById(notificationId);
    }

    public void sendTest(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Event testEvent = new Event("test", 0L);
        NotificationModel notification = new NotificationModel();
        notification.setType("test");

        notificatorManager.getAllTypes()
                .forEach(type -> notificatorManager.get(type).send(notification, user, testEvent, null));
    }

    public void sendTestByType(String type, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Event testEvent = new Event("test", 0L);
        NotificationModel notification = new NotificationModel();
        notification.setType("test");

        notificatorManager.get(type).send(notification, user, testEvent, null);
    }

    public List<Typed> getEventTypes() {
        List<Typed> types = new LinkedList<>();
        Field[] fields = Event.class.getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers()) && field.getName().startsWith("TYPE_")) {
                try {
                    types.add(new Typed(field.get(null).toString()));
                } catch (IllegalAccessException e) {
                    // log en producción
                }
            }
        }
        return types;
    }

    public List<Typed> getNotificators(boolean filterAnnouncement) {
        Set<String> announcementsUnsupported = Set.of("command", "web", "Mail");

        return notificatorManager.getAllTypes().stream()
                .filter(type -> !filterAnnouncement || !announcementsUnsupported.contains(type))
                .map(Typed::new)
                .collect(Collectors.toList());
    }

    public void sendMessage(String notificator, List<Long> userIds, NotificationMessage message, Long currentUserId) {
        List<User> users;

        if (userIds == null || userIds.isEmpty()) {
            users = userRepository.findAll(); // Si estás como admin y querés enviar a todos
        } else {
            users = userIds.stream()
                    .map(id -> userRepository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException("User ID " + id + " not found")))
                    .collect(Collectors.toList());
        }

        for (User user : users) {
            if (!user.isTemporary()) {
                notificatorManager.get(notificator).send(user, message, null, null);
            }
        }
    }

}