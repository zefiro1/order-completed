package com.ordercompleted.ports.primary;

public interface CompleteOrderUseCase {
  void completeOrder(String orderId, String productId, int quantity);
}
