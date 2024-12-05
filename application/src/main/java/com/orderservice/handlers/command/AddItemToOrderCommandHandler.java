package com.orderservice.handlers.command;

import com.orderservice.domain.model.Order;
import com.orderservice.domain.service.OrderDomainService;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddItemToOrderCommandHandler implements CommandHandler<AddItemToOrderCommand> {
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;

    @Override
    public void handle(AddItemToOrderCommand command) {
        Order order = orderRepository.findById(command.orderId());
        order.addItem(command.productId(), command.quantity());
        //double totalAmount = orderDomainService.calculateTotalAmount(order);
        //order.setTotalAmount(totalAmount);
        orderRepository.save(order);
    }
}
