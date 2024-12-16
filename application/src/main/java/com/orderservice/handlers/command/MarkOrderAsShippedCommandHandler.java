package com.orderservice.handlers.command;

import com.orderservice.domain.model.Order;
import com.orderservice.domain.model.OrderStatus;
import com.orderservice.ports.secondary.NotificationService;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsShippedCommandHandler implements CommandHandler<MarkOrderAsShippedCommand> {
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    @Override
    public void handle(MarkOrderAsShippedCommand command) {
        Order order = orderRepository.findById(command.orderId());
        order.markAsShipped();
        orderRepository.save(order);
        notificationService.sendOrderStatusNotification(command.orderId(), OrderStatus.SHIPPED);
    }
}
