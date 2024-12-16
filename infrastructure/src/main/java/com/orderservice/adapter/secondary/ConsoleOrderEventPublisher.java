package com.orderservice.adapter.secondary;


import com.orderservice.domain.event.OrderCompletedEvent;
import com.orderservice.ports.secondary.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Configuration;

import static com.orderservice.config.AppConfig.EXCHANGE;

@Configuration
@RequiredArgsConstructor
public class ConsoleOrderEventPublisher implements OrderEventPublisher {
    private final AmqpTemplate amqpTemplate;
    private static final String ROUTING_KEY_EXAMPLE = "example_routing_key";

    @SneakyThrows
    @Override
    public void publish(OrderCompletedEvent event) {
        amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_EXAMPLE, event);
        System.out.printf("Order Completed Event Published for Order Id: %s%n", event.orderId());
    }

}
