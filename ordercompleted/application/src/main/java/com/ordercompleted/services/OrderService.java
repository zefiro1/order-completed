package com.ordercompleted.services;

import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.service.OrderDomainService;
import com.ordercompleted.domain.service.PaymentDomainService;
import com.ordercompleted.handlers.command.order.*;
import com.ordercompleted.handlers.command.payment.ConfirmPaymentCommand;
import com.ordercompleted.handlers.command.payment.ConfirmPaymentCommandHandler;
import com.ordercompleted.ports.primary.OrderServiceUseCase;
import com.ordercompleted.ports.secondary.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderService implements OrderServiceUseCase {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final OrderEventPublisher orderEventPublisher;
  private final PaymentProvider paymentProvider;
  private final NotificationService notificationService;

  private final CommandQueryBus commandQueryBus;
  private final OrderDomainService orderDomainService;

  @Override
  public void createOrder(String orderId, String userId) {
    CreateOrderCommand command = new CreateOrderCommand(orderId, userId);
    commandQueryBus.dispatchCommand(command, new CreateOrderCommandHandler(orderRepository));
  }

  @Override
  public void addItemToOrder(String orderId, String productId, int quantity) {
    AddItemToOrderCommand command = new AddItemToOrderCommand(orderId, productId, quantity);
    commandQueryBus.dispatchCommand(command, new AddItemToOrderCommandHandler(orderRepository));
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
  public void markOrderAsPaid(String orderId, double amount) {
    ConfirmPaymentCommand command = new ConfirmPaymentCommand(orderId, amount);
    commandQueryBus.dispatchCommand(command,
        new ConfirmPaymentCommandHandler(orderRepository, userRepository, notificationService, new PaymentDomainService(paymentProvider)));
  }

  @Override
  public void markOrderAsShipped(String orderId) {
    MarkOrderAsShippedCommand command = new MarkOrderAsShippedCommand(orderId);
    commandQueryBus.dispatchCommand(command, new MarkOrderAsShippedCommandHandler(orderRepository, userRepository, notificationService));
  }

  @Override
  public void markOrderAsCompleted(String orderId) {
    MarkOrderAsCompletedCommand command = new MarkOrderAsCompletedCommand(orderId);
    commandQueryBus.dispatchCommand(command,
        new MarkOrderAsCompletedCommandHandler(orderRepository, userRepository, notificationService, orderEventPublisher, orderDomainService));
  }
}
