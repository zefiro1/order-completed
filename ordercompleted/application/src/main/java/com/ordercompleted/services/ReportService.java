package com.ordercompleted.services;

import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.CustomerSalesReport;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.ProductSalesReport;
import com.ordercompleted.handlers.query.report.*;
import com.ordercompleted.ports.primary.ReportServiceUseCase;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReportService implements ReportServiceUseCase {
  private final OrderRepository orderRepository;
  private final CommandQueryBus commandQueryBus;
  private final TaxService taxService;

  @Override
  public double getTotalSales() {
    GetTotalSalesQuery query = new GetTotalSalesQuery();
    return commandQueryBus.dispatchQuery(query, new GetTotalSalesQueryHandler(orderRepository));
  }

  @Override
  public double getTotalRevenue() {
    GetTotalRevenueQuery query = new GetTotalRevenueQuery();
    return commandQueryBus.dispatchQuery(query, new GetTotalRevenueQueryHandler(orderRepository, taxService));
  }

  @Override
  public List<ProductSalesReport> getTopSellingProducts() {
    GetTopSellingProductsQuery query = new GetTopSellingProductsQuery();
    return commandQueryBus.dispatchQuery(query, new GetTopSellingProductsQueryHandler(orderRepository));
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public List<CustomerSalesReport> getFrequentCustomers() {
    GetFrequentCustomersQuery query = new GetFrequentCustomersQuery();
    return commandQueryBus.dispatchQuery(query, new GetFrequentCustomersQueryHandler(orderRepository));
  }

  @Override
  public long getCompletedOrdersCount() {
    GetCompletedOrdersCountQuery query = new GetCompletedOrdersCountQuery();
    return commandQueryBus.dispatchQuery(query, new GetCompletedOrdersCountQueryHandler(orderRepository));
  }

  @Override
  public long getCancelledOrdersCount() {
    GetCancelledOrdersCountQuery query = new GetCancelledOrdersCountQuery();
    return commandQueryBus.dispatchQuery(query, new GetCancelledOrdersCountQueryHandler(orderRepository));
  }
}
