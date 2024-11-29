package com.ordercompleted.services;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.ports.primary.GetOrderUseCase;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOrderService implements GetOrderUseCase {
  private final OrderRepository orderRepository;

  @Override
  public String getOrderStatus(String orderId) {
    Order order = orderRepository.findById(orderId);
    return order.getStatus();
  }
}
