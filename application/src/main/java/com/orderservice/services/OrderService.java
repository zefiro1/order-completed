package com.orderservice.services;

import com.orderservice.dispatcher.CommandQueryBus;
import com.orderservice.domain.service.OrderDomainService;
import com.orderservice.handlers.command.*;
import com.orderservice.ports.primary.OrderServiceUseCase;
import com.orderservice.ports.secondary.NotificationService;
import com.orderservice.ports.secondary.OrderEventPublisher;
import com.orderservice.ports.secondary.OrderRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderService implements OrderServiceUseCase {
  private final OrderRepository orderRepository;
  private final OrderEventPublisher orderEventPublisher;
  private final NotificationService notificationService;

  private final CommandQueryBus commandQueryBus;
  private final OrderDomainService orderDomainService;

  @Override
  public void createOrder(String orderId, String userId) {
    CreateOrderCommand command = new CreateOrderCommand(orderId, userId);
    commandQueryBus.dispatchCommand(command, new CreateOrderCommandHandler(orderRepository, orderDomainService));
  }

  @Override
  public void addItemToOrder(String orderId, String productId, int quantity) {
    AddItemToOrderCommand command = new AddItemToOrderCommand(orderId, productId, quantity);
    commandQueryBus.dispatchCommand(command, new AddItemToOrderCommandHandler(orderRepository, orderDomainService));
  }

  @Override
  public void updateItemQuantityInOrder(String orderId, String productId, int newQuantity) {
    UpdateItemQuantityInOrderCommand command = new UpdateItemQuantityInOrderCommand(orderId, productId, newQuantity);
    commandQueryBus.dispatchCommand(command, new UpdateItemQuantityInOrderCommandHandler(orderRepository));
  }

  @Override
  public void removeItemFromOrder(String orderId, String productId) {
    RemoveItemFromOrderCommand command = new RemoveItemFromOrderCommand(orderId, productId);
    commandQueryBus.dispatchCommand(command, new RemoveItemFromOrderCommandHandler(orderRepository));
  }

  @Override
  public void cancelOrder(String orderId) {
    CancelOrderCommand command = new CancelOrderCommand(orderId);
    commandQueryBus.dispatchCommand(command, new CancelOrderCommandHandler(orderRepository));
  }

  @Override
  public void markOrderAsPaid(String orderId) {
    //TODO implementar el paid

  }

  @Override
  public void markOrderAsShipped(String orderId) {
    MarkOrderAsShippedCommand command = new MarkOrderAsShippedCommand(orderId);
    commandQueryBus.dispatchCommand(command, new MarkOrderAsShippedCommandHandler(orderRepository, notificationService));
  }

  @Override
  public void markOrderAsCompleted(String orderId) {
    MarkOrderAsCompletedCommand command = new MarkOrderAsCompletedCommand(orderId);
    commandQueryBus.dispatchCommand(command,
        new MarkOrderAsCompletedCommandHandler(orderRepository, notificationService, orderEventPublisher, orderDomainService));
  }

  @Override
  public void getOrderTotalAmount(String orderId) {
    //  orderDomainService.calculateTotalAmount(orderRepository.findById(orderId));
  }
}
