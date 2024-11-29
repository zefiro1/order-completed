package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.ports.secondary.OrderEventPublisher;

public class ConsoleOrderEventPublisher implements OrderEventPublisher {
  @Override
  public void publish(OrderCompletedEvent event) {
    System.out.printf("Order Completed Event Published for Order Id: %s%n", event.getOrderId());
  }
}
