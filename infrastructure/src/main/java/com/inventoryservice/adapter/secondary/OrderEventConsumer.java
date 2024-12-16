package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.event.OrderCompletedEvent;
import com.ordercompleted.ports.primary.OrderServiceUseCase;
import com.ordercompleted.ports.secondary.OrderCompletedEventProcessor;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@AllArgsConstructor
public class OrderEventConsumer implements OrderCompletedEventProcessor {
  private static final String QUEUE_NAME = "order_completed_queue";
  private static final String RABBITMQ_HOST = "localhost";
  private static final String USERNAME = "user";
  private static final String PASSWORD = "password";
  private static final String VIRTUAL_HOST = "/";
  private final OrderServiceUseCase orderServiceUseCase;
  @Override
  public void processOrderCompletedEvent(OrderCompletedEvent event) {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(RABBITMQ_HOST);
    factory.setUsername(USERNAME);
    factory.setPassword(PASSWORD);
    factory.setVirtualHost(VIRTUAL_HOST);

    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

      channel.queueDeclare(QUEUE_NAME, true, false, false, null);
      System.out.println(" [*] Esperando mensajes en la cola '" + QUEUE_NAME + "'...");

      DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        System.out.println(" [x] Recibido: '" + message + "'");

        processOrderCompletedEvent(message);
      };

      channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
      });

    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
    }
  }
  private void processOrderCompletedEvent(String message) {
    String orderId = extractOrderIdFromMessage(message);
    orderServiceUseCase.markOrderAsCompleted(orderId);
  }
  private String extractOrderIdFromMessage(String message) {
    return message.split(":")[1].trim();
  }

}
