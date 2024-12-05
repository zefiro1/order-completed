package com.inventoryservice.adapter.secondary;

import com.inventoryservice.ports.secondary.NotificationService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompositeNotificationService implements NotificationService {
    private final List<NotificationService> notificationServices;

    @Override
    public void sendInventoryAlert(String adminEmail, String productId, int stock) {
        notificationServices.forEach(notificationService -> notificationService.sendInventoryAlert(adminEmail, productId, stock));
    }
}
