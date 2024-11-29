package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.ports.secondary.InventoryService;
import com.ordercompleted.ports.secondary.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryService implements InventoryService {
  private final Map<String, Integer> inventory = new HashMap<>();
  private final ProductRepository productRepository;

  public InMemoryInventoryService(ProductRepository productRepository) {
    this.productRepository = productRepository;
    reloadInventory();
  }

  @Override
  public void reduceStock(String productId, int quantity) {
    Product product = productRepository.findById(productId);
    product.reduceStock(quantity);
    inventory.put(productId, inventory.get(productId) - quantity);
    productRepository.save(product);
  }
  @Override
  public void reloadInventory() {
    inventory.clear();
    productRepository.findAll().forEach(product -> inventory.put(product.getId(), product.getStock()));
  }
}
