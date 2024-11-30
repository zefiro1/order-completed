package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.model.Payment;

public interface PaymentProvider {
  Payment processPayment(String orderId, double amount);
}
