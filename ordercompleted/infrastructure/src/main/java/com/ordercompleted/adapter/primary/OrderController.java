package com.ordercompleted.adapter.primary;

import com.ordercompleted.ports.primary.CompleteOrderUseCase;
import com.ordercompleted.ports.primary.GetOrderUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderController {

  private final CompleteOrderUseCase completeOrderUseCase;
  private final GetOrderUseCase getOrderUseCase;

  public void completeOrder(String orderId, String productId, int quantity) {
    completeOrderUseCase.completeOrder(orderId,productId,quantity);
  }

  public String getOrderStatus(String orderId) {
    return getOrderUseCase.getOrderStatus(orderId);
  }
}
