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
  private double price;
  public void reduceStock(int quantity) {
    if (quantity > stock) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + id);
    }
    stock -= quantity;
  }

  public boolean isLowStock() {
    return stock < lowStockThreshold;
  }
}
