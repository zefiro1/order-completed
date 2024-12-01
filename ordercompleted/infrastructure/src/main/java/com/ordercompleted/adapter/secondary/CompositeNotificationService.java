package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.ports.secondary.NotificationService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompositeNotificationService implements NotificationService {
  private final List<NotificationService> notificationServices;

  @Override
  public void sendOrderStatusNotification(String userEmail, String orderId, OrderStatus orderStatus) {
    notificationServices.forEach(notificationService -> notificationService.sendOrderStatusNotification(userEmail, orderId, orderStatus));
  }

  @Override
  public void sendInventoryAlert(String adminEmail, String productId, int stock) {
    notificationServices.forEach(notificationService -> notificationService.sendInventoryAlert(adminEmail, productId, stock));
  }
}
