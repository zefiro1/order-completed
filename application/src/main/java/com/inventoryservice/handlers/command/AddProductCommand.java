package com.inventoryservice.handlers.command;

import com.inventoryservice.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddProductCommand {
  private final Product product;
}
