package com.ordercompleted.handlers.command.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MarkOrderAsPaidCommand {
  private final String orderId;
}
