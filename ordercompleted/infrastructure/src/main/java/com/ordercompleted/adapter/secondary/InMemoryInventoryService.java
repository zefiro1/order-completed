package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.service.ProductDomainService;
import com.ordercompleted.ports.secondary.InventoryService;
import com.ordercompleted.ports.secondary.ProductRepository;
import com.ordercompleted.services.AuditService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryService implements InventoryService {
  private final Map<String, Integer> inventory = new HashMap<>();
  private final ProductRepository productRepository;
  private final ProductDomainService productDomainService;
  private final AuditService auditService;

  public InMemoryInventoryService(ProductRepository productRepository, ProductDomainService productDomainService, AuditService auditService) {
    this.productRepository = productRepository;
    this.productDomainService = productDomainService;
    this.auditService = auditService;
    reloadInventory();
  }

  @Override
  public void reduceStock(String productId, int quantity) {
    productDomainService.reduceStock(productId, quantity);
    auditService.log("Inventory Update", "Admin", "Product ID: %s, Quantity Reduced: %d".formatted(productId, quantity), LocalDateTime.now());
  }

  @Override
  public void reloadInventory() {
    inventory.clear();
    productRepository.findAll().forEach(product -> inventory.put(product.getId(), product.getStock()));
  }
}
