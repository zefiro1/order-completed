package com.ordercompleted.domain.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Order {
  private final String id;
  private final String userId;
  private OrderStatus status;
  private final Map<String, OrderItem> items;

  public Order(String id, String userId) {
    this.id = id;
    this.userId = userId;
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
}
