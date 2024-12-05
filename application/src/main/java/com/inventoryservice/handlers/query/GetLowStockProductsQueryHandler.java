package com.inventoryservice.handlers.query;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.secondary.ProductRepository;
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
