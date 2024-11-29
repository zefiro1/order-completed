package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.event.OrderCompletedEvent;

public interface OrderCompletedEventProcessor {
  void processOrderCompletedEvent(OrderCompletedEvent event);
}
