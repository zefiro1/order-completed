package com.orderservice.ports.secondary;


import com.orderservice.domain.event.OrderCompletedEvent;

public interface OrderEventPublisher {
  void publish(OrderCompletedEvent event);
}
