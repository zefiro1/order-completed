package com.ordercompleted.ports.primary;

import com.ordercompleted.domain.model.Product;

import java.util.List;

public interface ManageInventoryUseCase {
  void addProduct(Product product);

  void updateProductStock(String productId, int newStock);

  List<Product> getLowStockProducts();

  List<Product> getAllProducts();

  Product getProductById(String productId);

  void deleteProduct(String productId);
}
