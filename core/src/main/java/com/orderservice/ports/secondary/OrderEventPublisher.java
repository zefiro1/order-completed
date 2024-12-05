package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.event.OrderCompletedEvent;

public interface OrderEventPublisher {
  void publish(OrderCompletedEvent event);
}
