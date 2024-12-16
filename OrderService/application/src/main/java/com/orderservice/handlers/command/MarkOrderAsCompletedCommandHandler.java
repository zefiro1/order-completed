package com.orderservice.handlers.command;

import com.orderservice.domain.event.OrderCompletedEvent;
import com.orderservice.domain.model.Order;
import com.orderservice.domain.model.OrderStatus;
import com.orderservice.domain.service.OrderDomainService;
import com.orderservice.ports.secondary.NotificationService;
import com.orderservice.ports.secondary.OrderEventPublisher;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarkOrderAsCompletedCommandHandler implements CommandHandler<MarkOrderAsCompletedCommand> {
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;
    private final OrderEventPublisher orderEventPublisher;
    private final OrderDomainService orderDomainService;

    @Override
    public void handle(MarkOrderAsCompletedCommand command) {
        Order order = orderRepository.findById(command.orderId());
        //  order.getItems().forEach((productId, orderItem) -> orderDomainService.completeOrder(productId, orderItem.getQuantity()));
        order.markAsCompleted();
        orderRepository.save(order);
        orderEventPublisher.publish(new OrderCompletedEvent(command.orderId(),"1",10));
        notificationService.sendOrderStatusNotification(order.getId(), OrderStatus.COMPLETED);
    }
}
