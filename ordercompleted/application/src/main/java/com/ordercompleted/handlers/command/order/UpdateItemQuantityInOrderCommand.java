package com.ordercompleted.handlers.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateItemQuantityInOrderCommand {
  private final String orderId;
  private final String productId;
  private final int newQuantity;
}
