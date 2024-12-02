package com.ordercompleted.handlers.query.product;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetProductQueryHandler implements QueryHandler<GetProductQuery, Product> {
  private final ProductRepository productRepository;

  @Override
  public Product handle(GetProductQuery query) {
    return productRepository.findById(query.getProductId());
  }
}
