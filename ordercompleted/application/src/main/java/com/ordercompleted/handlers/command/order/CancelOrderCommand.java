package com.ordercompleted.handlers.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CancelOrderCommand {
  private final String orderId;
}
