package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CancelOrderCommandHandler implements CommandHandler<CancelOrderCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(CancelOrderCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.cancel();
    orderRepository.save(order);
  }


}
