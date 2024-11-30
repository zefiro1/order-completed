package com.ordercompleted.handlers.command.payment;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.PaymentDomainService;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmPaymentCommandHandler implements CommandHandler<ConfirmPaymentCommand> {
  private final OrderRepository orderRepository;
  private final PaymentDomainService paymentDomainService;

  @Override
  public void handle(ConfirmPaymentCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    paymentDomainService.processOrderPayment(order, command.getAmount());
    orderRepository.save(order);

  }
}
