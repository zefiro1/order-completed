package com.ordercompleted.adapter.primary;

import com.ordercompleted.ports.primary.OrderServiceUseCase;
import com.ordercompleted.services.AuditService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class OrderController {

  private final OrderServiceUseCase orderServiceUseCase;
  private final AuditService auditService;

  // Crear una orden
  public void createOrder(String orderId, String userId) {
    orderServiceUseCase.createOrder(orderId, userId);
    auditService.log("Order Created", userId, "Order ID: %s".formatted(orderId), LocalDateTime.now());
  }

  // Agregar un artículo a la orden
  public void addItemToOrder(String orderId, String productId, int quantity) {
    orderServiceUseCase.addItemToOrder(orderId, productId, quantity);
  }

  // Actualizar la cantidad de un artículo en la orden
  public void updateItemQuantityInOrder(String orderId, String productId, int newQuantity) {
    orderServiceUseCase.updateItemQuantityInOrder(orderId, productId, newQuantity);
  }

  // Eliminar un artículo de la orden
  public void removeItemFromOrder(String orderId, String productId) {
    orderServiceUseCase.removeItemFromOrder(orderId, productId);
  }

  // Cancelar la orden
  public void cancelOrder(String orderId) {
    orderServiceUseCase.cancelOrder(orderId);
    auditService.log("Order Canceled", "User", "Order ID: %s".formatted(orderId), LocalDateTime.now());
  }

  // Marcar la orden como pagada
  public void confirmOrderPayment(String orderId) {
    orderServiceUseCase.markOrderAsPaid(orderId);
  }

  // Marcar la orden como enviada
  public void markOrderAsShipped(String orderId) {
    orderServiceUseCase.markOrderAsShipped(orderId);
  }

  // Marcar la orden como completada
  public void markOrderAsCompleted(String orderId) {
    orderServiceUseCase.markOrderAsCompleted(orderId);
  }

  public void getOrderTotalAmount(String orderId) {
    orderServiceUseCase.getOrderTotalAmount(orderId);
  }
}
