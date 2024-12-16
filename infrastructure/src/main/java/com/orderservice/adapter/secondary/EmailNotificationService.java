package com.orderservice.adapter.secondary;


import com.orderservice.domain.model.OrderStatus;
import com.orderservice.ports.secondary.NotificationService;

public class EmailNotificationService implements NotificationService {

    @Override
    public void sendOrderStatusNotification(String orderId, OrderStatus orderStatus) {
        System.out.println("Enviando correo al email asociado a esta orderId " + orderId);
    }


}
