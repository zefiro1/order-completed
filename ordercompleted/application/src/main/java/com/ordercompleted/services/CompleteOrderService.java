package com.ordercompleted.services;

import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.handlers.command.CompleteOrderCommand;
import com.ordercompleted.handlers.command.CompleteOrderCommandHandler;
import com.ordercompleted.ports.primary.CompleteOrderUseCase;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompleteOrderService implements CompleteOrderUseCase {

  private final CommandQueryBus commandQueryBus;
  private final OrderRepository orderRepository;
  private final OrderDomainService orderDomainService;
  private final OrderEventPublisher orderEventPublisher;

  @Override
  public void completeOrder(String orderId, String productId, int quantity) {
    CompleteOrderCommand command = new CompleteOrderCommand(orderId, productId, quantity);
    commandQueryBus.dispatchCommand(command, new CompleteOrderCommandHandler(orderRepository, orderDomainService, orderEventPublisher));
  }
}
