package com.ordercompleted.handlers.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CompleteOrderCommand {
  private final String orderId;
}
