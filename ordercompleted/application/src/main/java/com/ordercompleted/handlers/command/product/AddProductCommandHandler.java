package com.ordercompleted.handlers.command.product;

import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddProductCommandHandler implements CommandHandler<AddProductCommand> {
  private final ProductRepository productRepository;

  @Override
  public void handle(AddProductCommand command) {
    productRepository.save(command.getProduct());
  }
}