package com.ordercompleted.handlers.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateOrderCommand {
  private final String orderId;
  private final String userId;
}
