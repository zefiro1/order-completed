package com.inventoryservice.adapter.secondary;


import com.inventoryservice.ports.secondary.NotificationService;

public class SMSNotificationService implements NotificationService {


  @Override
  public void sendInventoryAlert(String adminEmail, String productId, int stock) {
    System.out.println("Enviando SMS al administrador " + adminEmail);
    System.out.println("Producto " + productId + " tiene solo " + stock + " unidades en inventario.");
  }
}
