package com.ordercompleted.handlers.command;

import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompleteOrderCommandHandler implements CommandHandler<CompleteOrderCommand>{
  private final OrderRepository orderRepository;
  private final OrderDomainService orderDomainService;
  private final OrderEventPublisher orderEventPublisher;
  @Override
  public void handle(CompleteOrderCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    orderDomainService.completeOrder(order);
    orderRepository.save(order);
    orderEventPublisher.publish(new OrderCompletedEvent(command.getOrderId()));
  }
}
