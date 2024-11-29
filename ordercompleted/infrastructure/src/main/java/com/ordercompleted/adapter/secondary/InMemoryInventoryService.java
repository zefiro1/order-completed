package com.ordercompleted.adapter.secondary;

import com.ordercompleted.ports.secondary.InventoryService;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryService implements InventoryService {
  private final Map<String, Integer> inventory = new HashMap<>();

  public InMemoryInventoryService() {
    inventory.put("product1", 10);
    inventory.put("product2", 5);
  }

  @Override
  public boolean checkStock(String productId, int quantity) {
    return inventory.getOrDefault(productId, 0) >= quantity;
  }

  @Override
  public void reduceStock(String productId, int quantity) {
    if (!checkStock(productId, quantity)) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + productId);
    }
    inventory.put(productId, inventory.get(productId) - quantity);
  }
}
