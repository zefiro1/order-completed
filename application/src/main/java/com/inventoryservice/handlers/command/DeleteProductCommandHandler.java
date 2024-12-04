package com.inventoryservice.handlers.command;

import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProductCommandHandler implements CommandHandler<DeleteProductCommand> {
  private final ProductRepository productRepository;

  @Override
  public void handle(DeleteProductCommand command) {
    productRepository.delete(command.getProductId());
  }
}
