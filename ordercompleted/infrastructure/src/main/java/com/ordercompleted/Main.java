package com.ordercompleted;

import com.ordercompleted.adapter.primary.OrderController;
import com.ordercompleted.adapter.primary.ReportController;
import com.ordercompleted.adapter.primary.UserController;
import com.ordercompleted.adapter.secondary.*;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.Role;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.domain.service.ProductDomainService;
import com.ordercompleted.services.OrderService;
import com.ordercompleted.services.ReportService;
import com.ordercompleted.services.TaxService;
import com.ordercompleted.services.UserService;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    InMemoryUserRepository userRepository = new InMemoryUserRepository();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    JwtTokenProvider tokenProvider = new JwtTokenProvider();
    CommandQueryBus commandQueryBus = new CommandQueryBus();
    UserService userServiceUseCase = new UserService(userRepository, passwordEncoder, tokenProvider, commandQueryBus);
    UserController userController = new UserController(userServiceUseCase);
    userController.register("jose.fg@developer.com", "jose--2", "Jose", Role.CLIENT);
    userController.login("jose.fg@developer.com", "jose--2");

    InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();
    ConsoleOrderEventPublisher orderCompletedEvent = new ConsoleOrderEventPublisher();
    StripePaymentProvider paymentProvider = new StripePaymentProvider();
    EmailNotificationService emailNotificationService = new EmailNotificationService();
    SMSNotificationService smsNotificationService = new SMSNotificationService();
    CompositeNotificationService compositeNotificationService = new CompositeNotificationService(List.of(emailNotificationService, smsNotificationService));
    InMemoryProductRepository productRepository = new InMemoryProductRepository();
    ProductDomainService productDomainService = new ProductDomainService(productRepository, compositeNotificationService);
    InMemoryInventoryService inventoryService = new InMemoryInventoryService(productRepository, productDomainService);
    OrderDomainService orderDomainService = new OrderDomainService(inventoryService, productRepository);
    OrderService orderService = new OrderService(orderRepository, userRepository, orderCompletedEvent, paymentProvider, compositeNotificationService,
        commandQueryBus, orderDomainService);

    System.out.println("Creando órdenes...");
    OrderController orderController = new OrderController(orderService);
    orderController.createOrder("1", "1");
    orderController.addItemToOrder("1", "1", 1);
    orderController.addItemToOrder("1", "2", 1);
    orderController.confirmOrderPayment("1");
    orderController.markOrderAsShipped("1");
    orderController.markOrderAsCompleted("1");

    TaxService taxService = new TaxService();
    ReportService reportServiceUseCase = new ReportService(orderRepository, commandQueryBus, taxService);
    ReportController reportController = new ReportController(reportServiceUseCase);
    double totalSales = reportController.getTotalSales();
    double totalRevenue = reportController.getTotalRevenue();
    List<Order> allOrders = reportController.getAllOrders();
    allOrders.forEach(order -> System.out.printf("Order ID: %s, Total: %.2f, Status: %s%n", order.getId(), order.getTotalAmount(), order.getStatus()));
    System.out.printf("Total Sales (Gross): %.2f%n", totalSales);
    System.out.printf("Total Revenue (Net): %.2f%n", totalRevenue);
    System.out.printf("Completed Orders: %s%n", reportController.getCompletedOrdersCount());
    System.out.printf("Canceled Orders: %s%n", reportController.getCancelledOrdersCount());
    System.out.printf("Top Selling Products: %s%n", reportController.getTopSellingProducts());
    System.out.printf("Frequent Customers: %s%n", reportController.getFrequentCustomers());

  }

}
