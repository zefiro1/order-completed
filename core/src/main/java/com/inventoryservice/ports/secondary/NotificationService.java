package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.model.OrderStatus;

public interface NotificationService {
  void sendOrderStatusNotification(String userEmail, String orderId, OrderStatus orderStatus);

  void sendInventoryAlert(String adminEmail, String productId, int stock);

}
