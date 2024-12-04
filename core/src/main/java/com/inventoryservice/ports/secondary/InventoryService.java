package com.inventoryservice.ports.secondary;

public interface InventoryService {
  void reduceStock(String productId, int quantity);

  void reloadInventory();
}
