package com.ordercompleted.handlers.query.report;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.OrderItem;
import com.ordercompleted.domain.model.OrderStatus;
import com.ordercompleted.domain.model.ProductSalesReport;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GetTopSellingProductsQueryHandler implements QueryHandler<GetTopSellingProductsQuery, List<ProductSalesReport>> {
  private final OrderRepository orderRepository;

  @Override
  public List<ProductSalesReport> handle(GetTopSellingProductsQuery query) {
    Map<String, Integer> productSales = new HashMap<>();

    for (Order order : orderRepository.findAll()) {
      if (order.getStatus() == OrderStatus.COMPLETED) {
        for (Map.Entry<String, OrderItem> entry : order.getItems().entrySet()) {
          String productId = entry.getKey();
          OrderItem item = entry.getValue();
          productSales.put(productId, productSales.getOrDefault(productId, 0) + item.getQuantity());
        }
      }
    }

    return productSales.entrySet().stream().map(entry -> new ProductSalesReport(entry.getKey(), entry.getValue()))
        .sorted((a, b) -> Integer.compare(b.getSalesCount(), a.getSalesCount())).toList();
  }
}

