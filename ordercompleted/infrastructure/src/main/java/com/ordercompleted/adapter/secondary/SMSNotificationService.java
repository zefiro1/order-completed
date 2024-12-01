package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.ports.secondary.NotificationService;

public class SMSNotificationService implements NotificationService {
  @Override
  public void sendOrderStatusNotification(String userEmail, String orderId, OrderStatus orderStatus) {
    // Simula el envío de un SMS al usuario
    System.out.println("Enviando SMS al número vinculado al correo " + userEmail);
    System.out.println("Estado de la orden " + orderId + " ha cambiado a: " + orderStatus.name());
  }

  @Override
  public void sendInventoryAlert(String adminEmail, String productId, int stock) {
    // Simula el envío de un SMS a un administrador
    System.out.println("Enviando SMS al administrador " + adminEmail);
    System.out.println("Producto " + productId + " tiene solo " + stock + " unidades en inventario.");
  }
}
