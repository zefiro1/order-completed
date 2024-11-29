package com.ordercompleted.handlers.command.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateProductStockCommand {
  private final String productId;
  private final int newStock;
}
