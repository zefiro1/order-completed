package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveItemFromOrderCommandHandler implements CommandHandler<RemoveItemFromOrderCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(RemoveItemFromOrderCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.removeItem(command.getProductId());
    orderRepository.delete(order);
  }
}
