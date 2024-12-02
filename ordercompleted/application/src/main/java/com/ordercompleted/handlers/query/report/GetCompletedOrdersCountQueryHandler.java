package com.ordercompleted.handlers.query.report;

import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetCompletedOrdersCountQueryHandler implements QueryHandler<GetCompletedOrdersCountQuery, Long> {

  private final OrderRepository orderRepository;

  @Override
  public Long handle(GetCompletedOrdersCountQuery query) {
    return orderRepository.findAll().stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED).count();
  }
}
