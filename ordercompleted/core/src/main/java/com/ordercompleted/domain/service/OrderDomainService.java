package com.ordercompleted.domain.service;

import com.ordercompleted.domain.model.Order;

public class OrderDomainService {
  public void completeOrder(Order order){
    order.complete();
  }
}
