package com.orderservice.config;

import com.orderservice.adapter.secondary.*;
import com.orderservice.dispatcher.CommandQueryBus;
import com.orderservice.domain.service.OrderDomainService;
import com.orderservice.ports.primary.OrderServiceUseCase;
import com.orderservice.ports.secondary.NotificationService;
import com.orderservice.ports.secondary.OrderEventPublisher;
import com.orderservice.ports.secondary.OrderRepository;
import com.orderservice.services.OrderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {
    public static final String EXCHANGE = "inventory.exchange";

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        final var template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
    @Bean
    public OrderRepository orderRepository() {
        return new InMemoryOrderRepository();
    }
    @Bean
    public OrderServiceUseCase orderServiceUseCase(AmqpTemplate amqpTemplate) {
        return new OrderService(orderRepository(),orderEventPublisher(amqpTemplate),notificationService(),new CommandQueryBus(),new OrderDomainService(amqpTemplate));
    }

    @Bean
    public OrderEventPublisher orderEventPublisher(AmqpTemplate amqpTemplate) {
        return new ConsoleOrderEventPublisher(amqpTemplate);
    }
    @Bean
    public NotificationService notificationService() {
        return new CompositeNotificationService(List.of(new SMSNotificationService(), new EmailNotificationService()));
    }
}
