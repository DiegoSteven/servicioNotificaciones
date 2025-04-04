package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.services.Notificators.Notificator;

@Service
public class NotificatorManager {

    private final Map<String, Notificator> notificators = new HashMap<>();

    @Autowired
    public NotificatorManager(List<Notificator> notificatorList) {
        for (Notificator notificator : notificatorList) {
            notificators.put(notificator.getType(), notificator);
        }
    }

    public Notificator get(String type) {
        Notificator n = notificators.get(type);
        if (n == null) {
            throw new IllegalArgumentException("Notificator not found: " + type);
        }
        return n;
    }

    public List<String> getAllTypes() {
        return new ArrayList<>(notificators.keySet());
    }
}