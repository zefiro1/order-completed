package com.orderservice.handlers.command;

import com.orderservice.domain.model.Order;
import com.orderservice.domain.service.OrderDomainService;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;

    @Override
    public void handle(CreateOrderCommand command) {
        Order order = new Order(command.orderId(), command.userId());
        //double totalAmount = orderDomainService.calculateTotalAmount(order);
        //order.setTotalAmount(totalAmount);
        orderRepository.save(order);

    }
}

