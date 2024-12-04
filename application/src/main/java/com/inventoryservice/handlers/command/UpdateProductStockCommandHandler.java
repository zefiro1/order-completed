package com.inventoryservice.handlers.command;

import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProductStockCommandHandler implements CommandHandler<UpdateProductStockCommand> {
  private final ProductRepository productRepository;
  @Override
  public void handle(UpdateProductStockCommand command) {
    Product product = productRepository.findById(command.getProductId());
    product.reduceStock(command.getNewStock());
    productRepository.save(product);
  }
}
