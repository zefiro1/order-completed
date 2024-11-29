package com.ordercompleted.handlers.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddItemToOrderCommand {
  private final String orderId;
  private final String productId;
  private final int quantity;
}
