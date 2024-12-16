package com.orderservice.handlers.command;

import com.orderservice.domain.model.Order;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateItemQuantityInOrderCommandHandler implements CommandHandler<UpdateItemQuantityInOrderCommand> {
    private final OrderRepository orderRepository;

    @Override
    public void handle(UpdateItemQuantityInOrderCommand command) {
        Order order = orderRepository.findById(command.orderId());
        order.updateItemQuantity(command.productId(), command.newQuantity());
        orderRepository.save(order);
    }
}
