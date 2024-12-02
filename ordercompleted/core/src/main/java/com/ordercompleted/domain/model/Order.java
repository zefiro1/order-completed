package com.ordercompleted.domain.model;

import com.ordercompleted.ports.secondary.ProductRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Order {
  private final String id;
  private final String customerId;
  private OrderStatus status;
  @Setter
  private double totalAmount;
  private final Map<String, OrderItem> items;

  public Order(String id, String customerId) {
    this.id = id;
    this.customerId = customerId;
    this.status = OrderStatus.PENDING;
    items = new HashMap<>();
  }

  public void addItem(String productId, int quantity) {
    items.put(productId, new OrderItem(productId, quantity));
  }

  public void removeItem(String productId) {
    items.remove(productId);
  }

  public void updateItemQuantity(String productId, int newQuantity) {
    OrderItem item = items.get(productId);
    if (item != null) {
      item.setQuantity(newQuantity);
    }
  }

  public void cancel() {
    if (status != OrderStatus.COMPLETED) {
      this.status = OrderStatus.CANCELLED;
    } else {
      throw new IllegalStateException("Cannot cancel a completed order");
    }
  }

  public void markAsPaid() {
    if (status == OrderStatus.PENDING) {
      this.status = OrderStatus.PAID;
    }
  }

  public void markAsShipped() {
    if (status == OrderStatus.PAID) {
      this.status = OrderStatus.SHIPPED;
    }
  }

  public void markAsCompleted() {
    if (status == OrderStatus.SHIPPED) {
      this.status = OrderStatus.COMPLETED;
    }
  }

  public boolean isCancelable() {
    return status == OrderStatus.PENDING || status == OrderStatus.PAID;
  }

  public double calculateTotalAmount(ProductRepository productRepository) {
    return items.entrySet().stream().mapToDouble(entry -> {
      Product product = productRepository.findById(entry.getKey());
      return product.getPrice() * entry.getValue().getQuantity();
    }).sum();
  }

}
