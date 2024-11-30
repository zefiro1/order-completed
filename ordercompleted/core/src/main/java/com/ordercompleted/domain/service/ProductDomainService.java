package com.ordercompleted.domain.service;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.ports.secondary.NotificationService;
import com.ordercompleted.ports.secondary.ProductRepository;
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
