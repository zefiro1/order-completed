package com.orderservice.ports.secondary;


import com.orderservice.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    Order findById(String id);

    void save(Order order);

    void delete(Order order);

    List<Order> findAll();
}
