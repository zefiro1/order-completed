package com.ordercompleted;

import com.ordercompleted.adapter.primary.OrderController;
import com.ordercompleted.adapter.secondary.ConsoleOrderEventPublisher;
import com.ordercompleted.adapter.secondary.InMemoryInventoryService;
import com.ordercompleted.adapter.secondary.InMemoryOrderRepository;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.ports.secondary.InventoryService;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import com.ordercompleted.services.CompleteOrderService;
import com.ordercompleted.services.GetOrderService;

public class Main {
  public static void main(String[] args) {

    OrderRepository orderRepository = new InMemoryOrderRepository();
    OrderEventPublisher orderEventPublisher = new ConsoleOrderEventPublisher();
    InventoryService inventoryServicePort = new InMemoryInventoryService();
    OrderDomainService orderDomainService = new OrderDomainService(inventoryServicePort);

    CommandQueryBus commandQueryBus = new CommandQueryBus();

    CompleteOrderService completeOrderService = new CompleteOrderService(
        commandQueryBus, orderRepository, orderDomainService, orderEventPublisher);
    GetOrderService getOrderService = new GetOrderService(commandQueryBus, orderRepository);

    OrderController orderController = new OrderController(completeOrderService, getOrderService);

    // Simulación
    orderRepository.save(new Order("1"));
    orderController.completeOrder("1", "product1", 2);
    System.out.println("Estado de la orden: " + orderController.getOrderStatus("1"));

  }
}
