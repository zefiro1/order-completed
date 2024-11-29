package com.ordercompleted.adapter.primary;

import com.ordercompleted.adapter.secondary.ConsoleOrderEventPublisher;
import com.ordercompleted.adapter.secondary.InMemoryOrderRepository;
import com.ordercompleted.domain.model.Order;
import com.ordercompleted.services.CompleteOrderService;
import com.ordercompleted.services.GetOrderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderControllerTest {
  private static final InMemoryOrderRepository inMemoryOrderRepository = new InMemoryOrderRepository();
  private static final ConsoleOrderEventPublisher consoleOrderEventPublisher = new ConsoleOrderEventPublisher();
  private static final CompleteOrderService completeOrderUseCase = new CompleteOrderService(inMemoryOrderRepository,consoleOrderEventPublisher);
  private static final GetOrderService getOrderUseCase = new GetOrderService(inMemoryOrderRepository);
  private static final OrderController orderController = new OrderController(completeOrderUseCase, getOrderUseCase);

  @BeforeAll
  static void init() {
    inMemoryOrderRepository.save(new Order("1"));
  }
  @Test
  void completeOrder() {
    orderController.completeOrder("1");
    String orderStatus = orderController.getOrderStatus("1");
    assertEquals("COMPLETED", orderStatus);
  }


}