package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateItemQuantityInOrderCommandHandler implements CommandHandler<UpdateItemQuantityInOrderCommand> {
  private final OrderRepository orderRepository;

  @Override
  public void handle(UpdateItemQuantityInOrderCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.updateItemQuantity(command.getProductId(), command.getNewQuantity());
    orderRepository.save(order);
  }
}
