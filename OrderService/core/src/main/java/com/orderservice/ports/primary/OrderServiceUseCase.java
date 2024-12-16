package com.orderservice.ports.primary;

public interface OrderServiceUseCase {

    void createOrder(String orderId, String userId);

    void addItemToOrder(String orderId, String productId, int quantity);

    void updateItemQuantityInOrder(String orderId, String productId, int newQuantity);

    void removeItemFromOrder(String orderId, String productId);

    void cancelOrder(String orderId);

    void markOrderAsPaid(String orderId);

    void markOrderAsShipped(String orderId);

    void markOrderAsCompleted(String orderId);

    void getOrderTotalAmount(String orderId);
}