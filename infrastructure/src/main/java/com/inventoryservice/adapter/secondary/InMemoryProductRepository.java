package com.inventoryservice.adapter.secondary;


import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.secondary.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {
  private final Map<String, Product> productDatabase = new HashMap<>();

  public InMemoryProductRepository() {
    productDatabase.put("1", new Product("1", "PC", 10, 4, 100));
    productDatabase.put("2", new Product("2", "Laptop", 15, 4, 100));
  }

  @Override
  public void save(Product product) {
    productDatabase.put(product.getId(), product);
    System.out.printf("Product %s saved or updated: %s%n", product.getName(), product.getId());
  }

  @Override
  public Product findById(String productId) {
    return productDatabase.get(productId);
  }

  @Override
  public List<Product> findAll() {
    return List.copyOf(productDatabase.values());
  }

  @Override
  public void delete(String productId) {
    productDatabase.remove(productId);
  }
}
