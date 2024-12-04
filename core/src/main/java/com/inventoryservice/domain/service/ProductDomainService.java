package com.inventoryservice.domain.service;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.secondary.NotificationService;
import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductDomainService {
  private final ProductRepository productRepository;
  private final NotificationService notificationService;
  public void reduceStock(String productId, int quantity) {
    Product product = productRepository.findById(productId);

    if (quantity > product.getStock()) {
      throw new IllegalStateException("Stock insuficiente para el producto: " + productId);
    }

    product.reduceStock(quantity);

    if (product.isLowStock()) {
      notificationService.sendInventoryAlert("admin@company.com", productId, product.getStock());
    }
    productRepository.save(product);
  }
}
