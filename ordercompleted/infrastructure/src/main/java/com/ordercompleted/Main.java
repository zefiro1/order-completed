package com.ordercompleted;

import com.ordercompleted.adapter.primary.InventoryController;
import com.ordercompleted.adapter.primary.OrderController;
import com.ordercompleted.adapter.secondary.ConsoleOrderEventPublisher;
import com.ordercompleted.adapter.secondary.InMemoryInventoryService;
import com.ordercompleted.adapter.secondary.InMemoryOrderRepository;
import com.ordercompleted.adapter.secondary.InMemoryProductRepository;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Product;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.services.ManageInventoryService;
import com.ordercompleted.services.OrderService;

public class Main {
  public static void main(String[] args) {
    CommandQueryBus commandQueryBus = new CommandQueryBus();
    InMemoryProductRepository productRepository = new InMemoryProductRepository();

    InventoryController inventoryController = new InventoryController(new ManageInventoryService(commandQueryBus, productRepository));
    inventoryController.addProduct(new Product("1", "PC", 10, 1));

    InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();
    OrderController orderController = new OrderController(new OrderService(commandQueryBus, orderRepository, new ConsoleOrderEventPublisher(),
        new OrderDomainService(new InMemoryInventoryService(productRepository))));
    orderController.createOrder("1");
    orderController.addItemToOrder("1", "1", 1);
    orderController.markOrderAsPaid("1");
    orderController.markOrderAsShipped("1");
    orderController.markOrderAsCompleted("1");

    Product productById = inventoryController.getProductById("1");
    System.out.printf("Stock: %d%n", productById.getStock());
  }
}
