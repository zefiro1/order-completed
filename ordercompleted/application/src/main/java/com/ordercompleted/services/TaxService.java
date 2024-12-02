package com.ordercompleted.services;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaxService {
  private static final double TAX_RATE = 0.1; // 10%

  public double applyTax(double amount) {
    return amount * (1 - TAX_RATE);
  }
}
