package com.ordercompleted.handlers.command.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteProductCommand {
  private final String productId;
}
