package com.ordercompleted.handlers.query;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOrderQueryHandler implements QueryHandler<String> {
  private final OrderRepository orderRepository;

  @Override
  public String handle(Object query) {
    if (query instanceof GetOrderQuery) {
      GetOrderQuery getOrderQuery = (GetOrderQuery) query;
      Order order = orderRepository.findById(getOrderQuery.getOrderId());
      return order != null ? order.getStatus() : "Order not found";
    }
    return "Invalid query type";
  }
}
