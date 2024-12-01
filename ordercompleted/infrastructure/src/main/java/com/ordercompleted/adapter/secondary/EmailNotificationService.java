package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.ports.secondary.NotificationService;

public class EmailNotificationService implements NotificationService {

  @Override
  public void sendOrderStatusNotification(String userEmail, String orderId, OrderStatus orderStatus) {
    System.out.println("Enviando correo a " + userEmail);
    System.out.println("Estado de la orden " + orderId + " ha cambiado a: " + orderStatus.name());
  }

  @Override
  public void sendInventoryAlert(String adminEmail, String productId, int stock) {
    System.out.println("Enviando alerta a " + adminEmail);
    System.out.println("Producto " + productId + " tiene solo " + stock + " unidades en inventario.");
  }
}
