package com.ordercompleted.handlers.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CompleteOrderCommand {
  private final String orderId;
  private final String productId;
  private final int quantity;
}
