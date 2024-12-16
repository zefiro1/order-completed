package com.orderservice.handlers.command;

import com.orderservice.domain.model.Order;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CancelOrderCommandHandler implements CommandHandler<CancelOrderCommand> {
    private final OrderRepository orderRepository;

    @Override
    public void handle(CancelOrderCommand command) {
        Order order = orderRepository.findById(command.orderId());
        order.cancel();
        orderRepository.save(order);
    }


}
