package com.ordercompleted.services;

import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Product;
import com.ordercompleted.handlers.command.product.*;
import com.ordercompleted.handlers.query.product.GetLowStockProductsQuery;
import com.ordercompleted.handlers.query.product.GetLowStockProductsQueryHandler;
import com.ordercompleted.handlers.query.product.GetProductQuery;
import com.ordercompleted.handlers.query.product.GetProductQueryHandler;
import com.ordercompleted.ports.primary.ManageInventoryUseCase;
import com.ordercompleted.ports.secondary.ProductRepository;
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
}
