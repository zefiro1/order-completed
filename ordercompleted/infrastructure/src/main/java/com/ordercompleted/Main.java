package com.ordercompleted;

import com.ordercompleted.adapter.primary.InventoryController;
import com.ordercompleted.adapter.primary.OrderController;
import com.ordercompleted.adapter.secondary.ConsoleOrderEventPublisher;
import com.ordercompleted.adapter.secondary.InMemoryInventoryService;
import com.ordercompleted.adapter.secondary.InMemoryOrderRepository;
import com.ordercompleted.adapter.secondary.InMemoryProductRepository;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.model.Product;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.services.CompleteOrderService;
import com.ordercompleted.services.GetOrderService;
import com.ordercompleted.services.ManageInventoryService;

public class Main {
  public static void main(String[] args) {
    CommandQueryBus commandQueryBus = new CommandQueryBus();
    InMemoryProductRepository productRepository = new InMemoryProductRepository();

    InventoryController inventoryController = new InventoryController(new ManageInventoryService(commandQueryBus, productRepository));
    inventoryController.addProduct(new Product("1", "PC", 10, 1));

    InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();
    OrderController orderController = new OrderController(new CompleteOrderService(commandQueryBus, orderRepository,
        new OrderDomainService(new InMemoryInventoryService(productRepository)), new ConsoleOrderEventPublisher()), new GetOrderService(commandQueryBus,
        orderRepository));
    orderRepository.save(new Order("1"));
    inventoryController.getProductById("1");
    orderController.completeOrder("1", "1", 1);
    inventoryController.getProductById("1");
  }
}
