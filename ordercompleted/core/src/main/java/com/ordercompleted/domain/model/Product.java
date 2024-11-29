package com.ordercompleted.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Product {
  private String id;
  private String name;
  private int stock;
  private int lowStockThreshold;

  public void reduceStock(int quantity) {
    if (quantity > stock) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + id);
    }
    stock -= quantity;
    System.out.printf("Stock actualizado: %d%n", stock);
  }

  public boolean isLowStock() {
    return stock < lowStockThreshold;
  }
}
