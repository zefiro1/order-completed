package com.ordercompleted.domain.service;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.ports.secondary.InventoryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderDomainService {
  private final InventoryService inventoryService;

  public void completeOrder(Order order, String productId, int quantity) {
    if (!inventoryService.checkStock(productId, quantity)) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + productId);
    }
    order.complete();
    inventoryService.reduceStock(productId, quantity);
  }
}
