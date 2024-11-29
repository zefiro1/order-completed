package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddItemToOrderCommandHandler implements CommandHandler<AddItemToOrderCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(AddItemToOrderCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.addItem(command.getProductId(), command.getQuantity());
    orderRepository.save(order);
  }
}
