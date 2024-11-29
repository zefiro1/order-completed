package com.ordercompleted.handlers.command.product;

import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {
  private final ProductRepository productRepository;

  @Override
  public void handle(DeleteProductCommand command) {
    productRepository.delete(command.getProductId());
  }
}
