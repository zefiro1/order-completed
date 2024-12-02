package com.ordercompleted.handlers.query.report;

import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetCancelledOrdersCountQueryHandler implements QueryHandler<GetCancelledOrdersCountQuery, Long> {
  private final OrderRepository orderRepository;

  @Override
  public Long handle(GetCancelledOrdersCountQuery query) {
    return orderRepository.findAll().stream().filter(order -> order.getStatus() == OrderStatus.CANCELLED).count();
  }
}
