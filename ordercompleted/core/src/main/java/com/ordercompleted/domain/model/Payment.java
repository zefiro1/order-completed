package com.ordercompleted.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Payment {
  private String id;
  private String orderId;
  private double amount;
  private PaymentStatus status;
}

