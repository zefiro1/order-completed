package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.Payment;
import com.ordercompleted.domain.model.PaymentStatus;
import com.ordercompleted.ports.secondary.PaymentProvider;

import java.util.UUID;

public class StripePaymentProvider implements PaymentProvider {
  @Override
  public Payment processPayment(String orderId, double amount) {
    return new Payment(UUID.randomUUID().toString(), orderId, amount, PaymentStatus.PAID);
  }
}
