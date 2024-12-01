package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.service.ProductDomainService;
import com.ordercompleted.ports.secondary.InventoryService;
import com.ordercompleted.ports.secondary.ProductRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryService implements InventoryService {
  private final Map<String, Integer> inventory = new HashMap<>();
  private final ProductRepository productRepository;
  private final ProductDomainService productDomainService;

  public InMemoryInventoryService(ProductRepository productRepository, ProductDomainService productDomainService) {
    this.productRepository = productRepository;
    this.productDomainService = productDomainService;
    reloadInventory();
  }

  @Override
  public void reduceStock(String productId, int quantity) {
    productDomainService.reduceStock(productId, quantity);
  }

  @Override
  public void reloadInventory() {
    inventory.clear();
    productRepository.findAll().forEach(product -> inventory.put(product.getId(), product.getStock()));
  }
}
