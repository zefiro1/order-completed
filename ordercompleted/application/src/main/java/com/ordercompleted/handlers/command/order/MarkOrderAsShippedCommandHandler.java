package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsShippedCommandHandler implements CommandHandler<MarkOrderAsShippedCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(MarkOrderAsShippedCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.markAsShipped();
    orderRepository.save(order);
  }
}
