package com.ordercompleted.ports.secondary;

public interface InventoryService {
  boolean checkStock(String productId, int quantity);

  void reduceStock(String productId, int quantity);
}
