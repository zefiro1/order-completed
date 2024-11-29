package com.ordercompleted.services;

import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.handlers.query.GetOrderQuery;
import com.ordercompleted.handlers.query.GetOrderQueryHandler;
import com.ordercompleted.ports.primary.GetOrderUseCase;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOrderService implements GetOrderUseCase {
  private final CommandQueryBus commandQueryBus;
  private final OrderRepository orderRepository;
  @Override
  public String getOrderStatus(String orderId) {
    GetOrderQuery query = new GetOrderQuery(orderId);
    return commandQueryBus.dispatchQuery(query,new GetOrderQueryHandler(orderRepository));
  }
}
