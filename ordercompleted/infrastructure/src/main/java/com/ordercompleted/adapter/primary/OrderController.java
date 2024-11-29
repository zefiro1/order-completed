package com.ordercompleted.adapter.primary;

import com.ordercompleted.ports.primary.OrderServiceUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderController {

  private final OrderServiceUseCase orderServiceUseCase;

  // Crear una orden
  public void createOrder(String orderId) {
    orderServiceUseCase.createOrder(orderId);
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
  }

  // Marcar la orden como pagada
  public void markOrderAsPaid(String orderId) {
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
}
