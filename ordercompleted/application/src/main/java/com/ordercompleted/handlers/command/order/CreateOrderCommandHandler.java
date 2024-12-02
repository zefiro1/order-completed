package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
  private final OrderRepository orderRepository;
  private final OrderDomainService orderDomainService;

  @Override
  public void handle(CreateOrderCommand command) {
    Order order = new Order(command.getOrderId(), command.getUserId());
    double totalAmount = orderDomainService.calculateTotalAmount(order);
    order.setTotalAmount(totalAmount);
    orderRepository.save(order);

  }
}

