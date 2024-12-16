package com.inventoryservice.adapter.secondary;


import com.inventoryservice.ports.secondary.NotificationService;

public class EmailNotificationService implements NotificationService {


    @Override
    public void sendInventoryAlert(String adminEmail, String productId, int stock) {
        System.out.println("Enviando alerta a " + adminEmail);
        System.out.println("Producto " + productId + " tiene solo " + stock + " unidades en inventario.");
    }
}
