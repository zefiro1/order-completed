package com.ordercompleted.handlers.query.report;

import com.ordercompleted.domain.model.CustomerSalesReport;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GetFrequentCustomersQueryHandler implements QueryHandler<GetFrequentCustomersQuery, List<CustomerSalesReport>> {
  private final OrderRepository orderRepository;

  @Override
  public List<CustomerSalesReport> handle(GetFrequentCustomersQuery query) {
    Map<String, Integer> customerOrders = new HashMap<>();

    for (Order order : orderRepository.findAll()) {
      if (order.getStatus() == OrderStatus.COMPLETED) {
        customerOrders.put(order.getCustomerId(), customerOrders.getOrDefault(order.getCustomerId(), 0) + 1);
      }
    }

    return customerOrders.entrySet().stream().map(entry -> new CustomerSalesReport(entry.getKey(), entry.getValue()))
        .sorted((a, b) -> Integer.compare(b.getOrderCount(), a.getOrderCount())).toList();
  }
}

