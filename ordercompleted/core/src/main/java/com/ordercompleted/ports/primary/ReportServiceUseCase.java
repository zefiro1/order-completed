package com.ordercompleted.ports.primary;

import com.ordercompleted.domain.model.CustomerSalesReport;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.ProductSalesReport;

import java.util.List;

public interface ReportServiceUseCase {
  double getTotalSales();

  double getTotalRevenue();

  List<ProductSalesReport> getTopSellingProducts();

  List<Order> getAllOrders();

  List<CustomerSalesReport> getFrequentCustomers();

  long getCompletedOrdersCount();

  long getCancelledOrdersCount();

}
