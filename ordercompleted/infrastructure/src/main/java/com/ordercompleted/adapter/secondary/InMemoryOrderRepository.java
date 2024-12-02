package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.ports.secondary.OrderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOrderRepository implements OrderRepository {
  private final Map<String, Order> database = new HashMap<>();
  @Override
  public Order findById(String id) {
    return database.get(id);
  }
  @Override
  public void save(Order order) {
    database.put(order.getId(), order);
  }

  @Override
  public void delete(Order order) {
    database.remove(order.getId());
  }

  @Override
  public List<Order> findAll() {
    return new ArrayList<>(database.values());
  }
}
