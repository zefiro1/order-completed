package com.orderservice.adapter.secondary;


import com.orderservice.domain.model.OrderStatus;
import com.orderservice.ports.secondary.NotificationService;

public class SMSNotificationService implements NotificationService {
    @Override
    public void sendOrderStatusNotification(String orderId, OrderStatus orderStatus) {
        System.out.println("Enviando SMS al número vinculado a la orden " + orderId);
    }

}
