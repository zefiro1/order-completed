package com.inventoryservice.handlers.command;

import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddProductCommandHandler implements CommandHandler<AddProductCommand> {
    private final ProductRepository productRepository;

    @Override
    public void handle(AddProductCommand command) {
        productRepository.save(command.getProduct());
    }
}