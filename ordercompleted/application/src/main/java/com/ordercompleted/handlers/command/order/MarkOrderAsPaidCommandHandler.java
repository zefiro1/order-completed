package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsPaidCommandHandler implements CommandHandler<MarkOrderAsPaidCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(MarkOrderAsPaidCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.markAsPaid();
    orderRepository.save(order);
  }

}
