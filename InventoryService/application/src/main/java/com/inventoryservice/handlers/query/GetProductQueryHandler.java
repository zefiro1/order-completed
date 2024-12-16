package com.inventoryservice.handlers.query;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetProductQueryHandler implements QueryHandler<GetProductQuery, Product> {
    private final ProductRepository productRepository;

    @Override
    public Product handle(GetProductQuery query) {
        return productRepository.findById(query.getProductId());
    }
}
