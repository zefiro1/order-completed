package com.ordercompleted.adapter.primary;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.ports.primary.ManageInventoryUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class InventoryController {
  private final ManageInventoryUseCase manageInventoryUseCase;

  public void addProduct(Product product) {
    manageInventoryUseCase.addProduct(product);
  }

  public void updateProductStock(String productId, int stock) {
    manageInventoryUseCase.updateProductStock(productId, stock);
  }

  public List<Product> getLowStockProducts() {
    return manageInventoryUseCase.getLowStockProducts();
  }

  public List<Product> allProducts() {
    return manageInventoryUseCase.getAllProducts();
  }

  public Product getProductById(String productId) {
    return manageInventoryUseCase.getProductById(productId);
  }

  public void deleteProduct(String productId) {
    manageInventoryUseCase.deleteProduct(productId);
  }
}
