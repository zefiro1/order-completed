package com.inventoryservice.handlers.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteProductCommand {
  private final String productId;
}
