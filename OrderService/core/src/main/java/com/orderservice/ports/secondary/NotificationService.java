package com.orderservice.ports.secondary;


import com.orderservice.domain.model.OrderStatus;

public interface NotificationService {
    void sendOrderStatusNotification(String orderId, OrderStatus orderStatus);


}
