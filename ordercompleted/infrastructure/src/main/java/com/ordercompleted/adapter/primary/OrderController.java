package com.ordercompleted.adapter.primary;

import com.ordercompleted.services.CompleteOrderService;
import com.ordercompleted.services.GetOrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderController {

  private final CompleteOrderService completeOrderService;
  private final GetOrderService getOrderService;

  public void completeOrder(String orderId, String productId, int quantity) {
    completeOrderService.completeOrder(orderId,productId,quantity);
  }

  public String getOrderStatus(String orderId) {
    return getOrderService.getOrderStatus(orderId);
  }
}
