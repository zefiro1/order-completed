package com.inventoryservice.ports.secondary;


public interface NotificationService {

    void sendInventoryAlert(String adminEmail, String productId, int stock);

}
