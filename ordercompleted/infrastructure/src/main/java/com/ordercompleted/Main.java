package com.ordercompleted;

import com.ordercompleted.adapter.primary.InventoryController;
import com.ordercompleted.adapter.primary.OrderController;
import com.ordercompleted.adapter.primary.UserController;
import com.ordercompleted.adapter.secondary.*;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Product;
import com.ordercompleted.domain.model.Role;
import com.ordercompleted.domain.model.User;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.domain.service.ProductDomainService;
import com.ordercompleted.ports.secondary.NotificationService;
import com.ordercompleted.services.ManageInventoryService;
import com.ordercompleted.services.OrderService;
import com.ordercompleted.services.UserService;

import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    CommandQueryBus commandQueryBus = new CommandQueryBus();
    InMemoryProductRepository productRepository = new InMemoryProductRepository();

    InventoryController inventoryController = new InventoryController(new ManageInventoryService(commandQueryBus, productRepository));
    System.out.println("=".repeat(10));
    System.out.println("Add products to inventory");
    inventoryController.addProduct(new Product("1", "PC", 10, 4));
    System.out.println("=".repeat(10));
    NotificationService emailNotificationService = new EmailNotificationService();
    NotificationService smsNotificationService = new SMSNotificationService();
    List<NotificationService> notificationServices = Arrays.asList(emailNotificationService, smsNotificationService);
    NotificationService compositeNotificationService = new CompositeNotificationService(notificationServices);
    InMemoryUserRepository userRepository = new InMemoryUserRepository();

    UserController userController = new UserController(new UserService(userRepository, new BCryptPasswordEncoder(), new JwtTokenProvider(), commandQueryBus));
    System.out.println("=".repeat(10));
    System.out.println("Login User");
    userController.register("jose.fg@developer.com", "jose2--2", "Jose", Role.ADMIN);
    userController.login("jose.fg@developer.com", "jose2--2");
    userController.checkAdminAccess(new User("1", "jose.fg@developer.com", "jose2--2", "Jose", Role.ADMIN));

    InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();
    OrderController orderController = new OrderController(
        new OrderService(orderRepository, userRepository, new ConsoleOrderEventPublisher(), new StripePaymentProvider(), compositeNotificationService,
            commandQueryBus, new OrderDomainService(
            new InMemoryInventoryService(productRepository, new ProductDomainService(productRepository, compositeNotificationService)))));

    System.out.println("=".repeat(10));
    System.out.println("Order 1");
    orderController.createOrder("1", "1");
    orderController.addItemToOrder("1", "1", 6);
    orderController.confirmOrderPayment("1", 1800);
    orderController.markOrderAsShipped("1");
    orderController.markOrderAsCompleted("1");
    System.out.println("=".repeat(10));
    System.out.println("Order 2");
    orderController.createOrder("2", "1");
    orderController.addItemToOrder("2", "1", 4);
    orderController.confirmOrderPayment("2", 1800);
    orderController.markOrderAsShipped("2");
    orderController.markOrderAsCompleted("2");
    System.out.println("=".repeat(10));

    Product productById = inventoryController.getProductById("1");
    System.out.printf("Stock: %d%n", productById.getStock());

  }
}
