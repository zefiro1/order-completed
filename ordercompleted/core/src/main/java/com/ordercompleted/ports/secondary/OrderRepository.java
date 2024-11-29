package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.model.Order;

public interface OrderRepository {
  Order findById(String id);
  void save(Order order);
  void delete(Order order);
}
