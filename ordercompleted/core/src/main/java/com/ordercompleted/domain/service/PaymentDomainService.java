package com.ordercompleted.domain.service;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.domain.model.Payment;
import com.ordercompleted.domain.model.PaymentStatus;
import com.ordercompleted.ports.secondary.PaymentProvider;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PaymentDomainService {
  private final PaymentProvider paymentProvider;

  public Payment processOrderPayment(Order order, double amount) {
    if (order.getStatus() != OrderStatus.PENDING) {
      throw new IllegalStateException("Solo las órdenes pendientes pueden ser pagadas.");
    }

    Payment payment = paymentProvider.processPayment(order.getId(), amount);

    if (payment.getStatus() == PaymentStatus.PAID) {
      order.markAsPaid();
    } else {
      throw new IllegalStateException("El pago no fue exitoso.");
    }

    return payment;
  }
}
