package com.ordercompleted.services;

import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.ports.primary.CompleteOrderUseCase;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;

public class CompleteOrderService implements CompleteOrderUseCase {
  private final OrderRepository orderRepository;
  private final OrderEventPublisher orderEventPublisher;
  private final OrderDomainService orderDomainService;

   public CompleteOrderService(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
     this.orderRepository = orderRepository;
     this.orderEventPublisher = orderEventPublisher;
     orderDomainService = new OrderDomainService();
   }

   @Override
  public void completeOrder(String orderId) {
    Order order = orderRepository.findById(orderId);
    orderDomainService.completeOrder(order);
    orderRepository.save(order);
    orderEventPublisher.publish(new OrderCompletedEvent(orderId));
  }
}
