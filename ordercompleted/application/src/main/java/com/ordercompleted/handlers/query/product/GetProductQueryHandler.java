package com.ordercompleted.handlers.query.product;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetProductQueryHandler implements QueryHandler<Product> {
  private final ProductRepository productRepository;

  @Override
  public Product handle(Object query) {
    if (query instanceof GetProductQuery getProductQuery) {
      return productRepository.findById(getProductQuery.getProductId());
    }
    throw new IllegalArgumentException("Invalid query type");
  }
}
