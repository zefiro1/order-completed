package com.inventoryservice.services;

import com.inventoryservice.dispatcher.CommandQueryBus;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.handlers.command.*;
import com.inventoryservice.handlers.query.GetLowStockProductsQuery;
import com.inventoryservice.handlers.query.GetLowStockProductsQueryHandler;
import com.inventoryservice.handlers.query.GetProductQuery;
import com.inventoryservice.handlers.query.GetProductQueryHandler;
import com.inventoryservice.ports.primary.ManageInventoryUseCase;
import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ManageInventoryService implements ManageInventoryUseCase {
  private final CommandQueryBus commandQueryBus;
  private final ProductRepository productRepository;

  @Override
  public void addProduct(Product product) {
    AddProductCommand command = new AddProductCommand(product);
    commandQueryBus.dispatchCommand(command, new AddProductCommandHandler(productRepository));
  }

  @Override
  public void updateProductStock(String productId, int newStock) {
    UpdateProductStockCommand command = new UpdateProductStockCommand(productId, newStock);
    commandQueryBus.dispatchCommand(command, new UpdateProductStockCommandHandler(productRepository));
  }

  @Override
  public List<Product> getLowStockProducts() {
    GetLowStockProductsQuery query = new GetLowStockProductsQuery();
    return commandQueryBus.dispatchQuery(query, new GetLowStockProductsQueryHandler(productRepository));
  }

  @Override
  public Product getProductById(String productId) {
    GetProductQuery query = new GetProductQuery(productId);
    return commandQueryBus.dispatchQuery(query, new GetProductQueryHandler(productRepository));
  }

  @Override
  public void deleteProduct(String productId) {
    DeleteProductCommand command = new DeleteProductCommand(productId);
    commandQueryBus.dispatchCommand(command, new DeleteProductCommandHandler(productRepository));
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

}
