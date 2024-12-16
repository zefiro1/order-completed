package com.inventoryservice.config;

import com.inventoryservice.adapter.secondary.*;
import com.inventoryservice.dispatcher.CommandQueryBus;
import com.inventoryservice.domain.service.ProductDomainService;
import com.inventoryservice.ports.primary.ManageInventoryUseCase;
import com.inventoryservice.ports.secondary.InventoryService;
import com.inventoryservice.ports.secondary.NotificationService;
import com.inventoryservice.ports.secondary.ProductRepository;
import com.inventoryservice.services.ManageInventoryService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableRabbit
public class AppConfig {

    static final String EXCHANGE = "inventory.exchange";
    public static final String PRODUCT_REQUEST_QUEUE = "product.request.queue";
    @Bean
    public Queue queue() {
        return new Queue(PRODUCT_REQUEST_QUEUE, false);
    }
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("product.routing.key");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final var template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

    @Bean
    public ManageInventoryUseCase manageInventoryUseCase(ProductRepository productRepository) {
        return new ManageInventoryService(new CommandQueryBus(), productRepository, new ProductDomainService(productRepository, notificationService()));
    }

    @Bean
    public InventoryService inventoryService(ProductRepository productRepository, NotificationService notificationService) {
        return new InMemoryInventoryService(productRepository, new ProductDomainService(productRepository, notificationService));
    }

    @Bean
    public NotificationService notificationService() {
        return new CompositeNotificationService(List.of(new SMSNotificationService(), new EmailNotificationService()));
    }
}
