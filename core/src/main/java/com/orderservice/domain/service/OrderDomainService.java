package com.ordercompleted.domain.service;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.ports.secondary.InventoryService;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderDomainService {
  private final InventoryService inventoryService;
  private final ProductRepository productRepository;

  public void completeOrder(String productId, int quantity) {
    inventoryService.reduceStock(productId, quantity);
  }

  public double calculateTotalAmount(Order order) {
    return order.calculateTotalAmount(productRepository);
  }
}
