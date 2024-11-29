package com.ordercompleted.ports.primary;

public interface GetOrderUseCase {
  String getOrderStatus(String orderId);
}
