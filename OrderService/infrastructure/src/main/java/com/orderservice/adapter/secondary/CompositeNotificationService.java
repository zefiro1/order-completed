package com.orderservice.adapter.secondary;


import com.orderservice.domain.model.OrderStatus;
import com.orderservice.ports.secondary.NotificationService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompositeNotificationService implements NotificationService {
    private final List<NotificationService> notificationServices;

    @Override
    public void sendOrderStatusNotification(String orderId, OrderStatus orderStatus) {
        notificationServices.forEach(notificationService -> notificationService.sendOrderStatusNotification(orderId, orderStatus));
    }

}
