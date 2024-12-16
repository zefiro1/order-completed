package com.inventoryservice.ports.secondary;


import com.inventoryservice.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);

    Product findById(String productId);

    List<Product> findAll();

    void delete(String productId);
}
