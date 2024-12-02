package com.ordercompleted.handlers.query.product;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetLowStockProductsQueryHandler implements QueryHandler<GetLowStockProductsQuery, List<Product>> {
  private final ProductRepository productRepository;

  @Override
  public List<Product> handle(GetLowStockProductsQuery query) {
    return productRepository.findAll().stream().filter(Product::isLowStock).toList();

  }
}
