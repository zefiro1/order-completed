package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsCompletedCommandHandler implements CommandHandler<MarkOrderAsCompletedCommand> {
  private final OrderRepository orderRepository;
  private final OrderEventPublisher orderEventPublisher;
  private final OrderDomainService orderDomainService;
  @Override
  public void handle(MarkOrderAsCompletedCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.getItems().forEach((productId, orderItem) -> orderDomainService.completeOrder(order, productId, orderItem.getQuantity()));
    order.markAsCompleted();
    orderRepository.save(order);
    orderEventPublisher.publish(new OrderCompletedEvent(command.getOrderId()));
  }
}
