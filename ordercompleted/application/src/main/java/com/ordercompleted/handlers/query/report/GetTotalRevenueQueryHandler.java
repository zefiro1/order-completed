package com.ordercompleted.handlers.query.report;

import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import com.ordercompleted.services.TaxService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetTotalRevenueQueryHandler implements QueryHandler<GetTotalRevenueQuery, Double> {

  private final OrderRepository orderRepository;
  private final TaxService taxService;

  @Override
  public Double handle(GetTotalRevenueQuery query) {

    return orderRepository.findAll().stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED)
        .mapToDouble(order -> taxService.applyTax(order.getTotalAmount())).sum();

  }
}
