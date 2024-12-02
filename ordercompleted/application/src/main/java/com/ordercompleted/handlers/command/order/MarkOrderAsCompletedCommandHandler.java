package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.domain.model.User;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.NotificationService;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsCompletedCommandHandler implements CommandHandler<MarkOrderAsCompletedCommand> {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final NotificationService notificationService;
  private final OrderEventPublisher orderEventPublisher;
  private final OrderDomainService orderDomainService;
  @Override
  public void handle(MarkOrderAsCompletedCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.getItems().forEach((productId, orderItem) -> orderDomainService.completeOrder(productId, orderItem.getQuantity()));
    order.markAsCompleted();
    orderRepository.save(order);
    orderEventPublisher.publish(new OrderCompletedEvent(command.getOrderId()));
    User user = userRepository.findById(order.getCustomerId());
    notificationService.sendOrderStatusNotification(user.getEmail(), order.getId(), OrderStatus.COMPLETED);
  }
}
