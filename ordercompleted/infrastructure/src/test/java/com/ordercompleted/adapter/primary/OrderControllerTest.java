package com.ordercompleted.adapter.primary;

import com.ordercompleted.adapter.secondary.ConsoleOrderEventPublisher;
import com.ordercompleted.adapter.secondary.InMemoryOrderRepository;
import com.ordercompleted.adapter.secondary.OrderEventConsumer;
import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.ports.secondary.OrderEventPublisher;
import com.ordercompleted.ports.secondary.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
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
  // Crear el Consumer para los eventos de la cola
  static final OrderEventConsumer orderEventConsumer = new OrderEventConsumer(completeOrderService);
  @BeforeAll
  static void init() {
 //   orderRepository.save(new Order("1"));
  }

  @Test
  void completeOrder() throws InterruptedException {
    orderEventConsumer.processOrderCompletedEvent(new OrderCompletedEvent("3"));
    sleep(10000);
    String orderStatus = orderController.getOrderStatus("1");
    assertEquals("COMPLETED", orderStatus);
  }

}