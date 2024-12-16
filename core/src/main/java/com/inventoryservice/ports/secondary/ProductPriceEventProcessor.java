package com.inventoryservice.ports.secondary;


import com.inventoryservice.domain.event.OrderCompletedEvent;

public interface OrderCompletedEventProcessor {
  void processOrderCompletedEvent(OrderCompletedEvent event);
}
