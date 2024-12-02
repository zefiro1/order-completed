package com.ordercompleted.handlers.command.order;

import com.ordercompleted.domain.model.Order;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddItemToOrderCommandHandler implements CommandHandler<AddItemToOrderCommand> {
  private final OrderRepository orderRepository;
  private final OrderDomainService orderDomainService;

  @Override
  public void handle(AddItemToOrderCommand command) {
    Order order = orderRepository.findById(command.getOrderId());
    order.addItem(command.getProductId(), command.getQuantity());
    double totalAmount = orderDomainService.calculateTotalAmount(order);
    order.setTotalAmount(totalAmount);
    orderRepository.save(order);
  }
}
