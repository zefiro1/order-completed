package com.ordercompleted.handlers.command.payment;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.domain.model.User;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.domain.service.PaymentDomainService;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.NotificationService;
import com.ordercompleted.ports.secondary.OrderRepository;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConfirmPaymentCommandHandler implements CommandHandler<ConfirmPaymentCommand> {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final NotificationService notificationService;
  private final PaymentDomainService paymentDomainService;
  private final OrderDomainService orderDomainService;
  @Override
  public void handle(ConfirmPaymentCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    double totalAmount = orderDomainService.calculateTotalAmount(order);
    paymentDomainService.processOrderPayment(order, totalAmount);
    orderRepository.save(order);
    User user = userRepository.findById(order.getCustomerId());
    notificationService.sendOrderStatusNotification(user.getEmail(), order.getId(), OrderStatus.PAID);
  }
}
