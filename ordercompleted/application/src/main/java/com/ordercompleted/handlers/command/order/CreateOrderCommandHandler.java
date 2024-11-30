package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(CreateOrderCommand command) {
    Order order = new Order(command.getOrderId(), command.getUserId());
    orderRepository.save(order);
  }
}

