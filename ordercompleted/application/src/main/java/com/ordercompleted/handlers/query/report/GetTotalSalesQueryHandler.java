package com.ordercompleted.handlers.query.report;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetTotalSalesQueryHandler implements QueryHandler<GetTotalSalesQuery, Double> {
  private final OrderRepository orderRepository;

  @Override
  public Double handle(GetTotalSalesQuery query) {
    return orderRepository.findAll().stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED).mapToDouble(Order::getTotalAmount).sum();

  }
}
