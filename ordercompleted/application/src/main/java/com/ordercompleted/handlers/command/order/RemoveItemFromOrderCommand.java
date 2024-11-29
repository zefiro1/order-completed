package com.ordercompleted.handlers.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RemoveItemFromOrderCommand {
  private final String orderId;
  private final String productId;

}
