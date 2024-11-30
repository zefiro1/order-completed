package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.domain.model.User;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.NotificationService;
import com.ordercompleted.ports.secondary.OrderRepository;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsShippedCommandHandler implements CommandHandler<MarkOrderAsShippedCommand> {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final NotificationService notificationService;
  @Override
  public void handle(MarkOrderAsShippedCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.markAsShipped();
    orderRepository.save(order);
    User user = userRepository.findById(order.getUserId());
    notificationService.sendOrderStatusNotification(user.getEmail(), order.getId(), OrderStatus.SHIPPED);
  }
}
