package com.ordercompleted.handlers.command.product;

import com.ordercompleted.domain.model.Product;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.ProductRepository;
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
