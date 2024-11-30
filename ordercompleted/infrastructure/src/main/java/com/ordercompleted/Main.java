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
import com.ordercompleted.services.ManageInventoryService;
import com.ordercompleted.services.OrderService;
import com.ordercompleted.services.UserService;

public class Main {
  public static void main(String[] args) {
    CommandQueryBus commandQueryBus = new CommandQueryBus();
    InMemoryProductRepository productRepository = new InMemoryProductRepository();

    InventoryController inventoryController = new InventoryController(new ManageInventoryService(commandQueryBus, productRepository));
    inventoryController.addProduct(new Product("1", "PC", 10, 1));

    InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();
    OrderController orderController = new OrderController(new OrderService(commandQueryBus, orderRepository, new ConsoleOrderEventPublisher(),
        new OrderDomainService(new InMemoryInventoryService(productRepository)), new StripePaymentProvider()));
    orderController.createOrder("1");
    orderController.addItemToOrder("1", "1", 1);
    orderController.confirmOrderPayment("1", 1800);
    orderController.markOrderAsShipped("1");
    orderController.markOrderAsCompleted("1");

    Product productById = inventoryController.getProductById("1");
    System.out.printf("Stock: %d%n", productById.getStock());

    UserController userController = new UserController(
        new UserService(new InMemoryUserRepository(), new BCryptPasswordEncoder(), new JwtTokenProvider(), commandQueryBus));
    userController.register("jose.fg@developer.com", "jose2--2", "Jose", Role.ADMIN);
    userController.login("jose.fg@developer.com", "jose2--2");
    userController.checkAdminAccess(new User("1", "jose.fg@developer.com", "jose2--2", "Jose", Role.ADMIN));
  }
}
