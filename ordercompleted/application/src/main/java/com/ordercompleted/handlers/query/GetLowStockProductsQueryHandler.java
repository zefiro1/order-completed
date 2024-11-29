package com.ordercompleted.handlers.query;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetLowStockProductsQueryHandler implements QueryHandler<List<Product>> {
  private final ProductRepository productRepository;

  @Override
  public List<Product> handle(Object query) {
    if (query instanceof GetLowStockProductsQuery) {
      return productRepository.findAll().stream().filter(Product::isLowStock).toList();
    }
    throw new IllegalArgumentException("Invalid query type");
  }
}
