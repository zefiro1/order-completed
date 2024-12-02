package com.ordercompleted.handlers.command.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConfirmPaymentCommand {
  private final String orderId;
}
