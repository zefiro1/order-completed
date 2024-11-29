package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.ports.secondary.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {
  private final Map<String, Product> productDatabase = new HashMap<>();

  @Override
  public void save(Product product) {
    productDatabase.put(product.getId(), product);
    System.out.printf("Product saved:%s%n", product.getId());
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
