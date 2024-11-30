package com.ordercompleted.ports.primary;

public interface OrderServiceUseCase {

  void createOrder(String orderId);

  void addItemToOrder(String orderId, String productId, int quantity);

  void updateItemQuantityInOrder(String orderId, String productId, int newQuantity);

  void removeItemFromOrder(String orderId, String productId);

  void cancelOrder(String orderId);

  void markOrderAsPaid(String orderId, double amount);

  void markOrderAsShipped(String orderId);

  void markOrderAsCompleted(String orderId);
}