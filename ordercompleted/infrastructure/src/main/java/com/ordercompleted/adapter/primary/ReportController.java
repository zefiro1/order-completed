package com.ordercompleted.adapter.primary;

import com.ordercompleted.domain.model.CustomerSalesReport;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.ProductSalesReport;
import com.ordercompleted.ports.primary.ReportServiceUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ReportController {

  private final ReportServiceUseCase reportServiceUseCase;

  public double getTotalSales() {
    return reportServiceUseCase.getTotalSales();
  }

  public double getTotalRevenue() {
    return reportServiceUseCase.getTotalRevenue();
  }

  public List<ProductSalesReport> getTopSellingProducts() {
    return reportServiceUseCase.getTopSellingProducts();
  }

  public List<CustomerSalesReport> getFrequentCustomers() {
    return reportServiceUseCase.getFrequentCustomers();
  }

  public List<Order> getAllOrders() {
    return reportServiceUseCase.getAllOrders();
  }

  public long getCompletedOrdersCount() {
    return reportServiceUseCase.getCompletedOrdersCount();
  }

  public long getCancelledOrdersCount() {
    return reportServiceUseCase.getCancelledOrdersCount();
  }
}
