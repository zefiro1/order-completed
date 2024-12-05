package com.orderservice.handlers.command;

import com.orderservice.domain.model.Order;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveItemFromOrderCommandHandler implements CommandHandler<RemoveItemFromOrderCommand> {
    private final OrderRepository orderRepository;

    @Override
    public void handle(RemoveItemFromOrderCommand command) {
        Order order = orderRepository.findById(command.orderId());
        order.removeItem(command.productId());
        orderRepository.delete(order);
    }
}
