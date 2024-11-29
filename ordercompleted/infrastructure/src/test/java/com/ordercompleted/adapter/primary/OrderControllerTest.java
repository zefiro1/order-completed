package com.ordercompleted.adapter.primary;

import com.ordercompleted.adapter.secondary.ConsoleOrderEventPublisher;
import com.ordercompleted.adapter.secondary.InMemoryOrderRepository;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.handlers.command.CompleteOrderCommandHandler;
import com.ordercompleted.handlers.query.GetOrderQueryHandler;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import com.ordercompleted.services.CompleteOrderService;
import com.ordercompleted.services.GetOrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderControllerTest {
  // Crear instancias de los adaptadores
  static final OrderRepository orderRepository = new InMemoryOrderRepository();
  static final OrderEventPublisher orderEventPublisher = new ConsoleOrderEventPublisher();
  static final OrderDomainService orderDomainService = new OrderDomainService();

  // Crear el CommandQueryBus
  static final CommandQueryBus commandQueryBus = new CommandQueryBus();
  // Crear los servicios
  static final CompleteOrderService completeOrderService = new CompleteOrderService(commandQueryBus, orderRepository, orderDomainService, orderEventPublisher);
  static final GetOrderService getOrderService = new GetOrderService(commandQueryBus, orderRepository);

  // Crear el controlador
  static final OrderController orderController = new OrderController(completeOrderService, getOrderService);

  @BeforeAll
  static void init() {
    orderRepository.save(new Order("1"));
  }

  @Test
  void completeOrder() {
    orderController.completeOrder("1");
    String orderStatus = orderController.getOrderStatus("1");
    assertEquals("COMPLETED", orderStatus);
  }

}