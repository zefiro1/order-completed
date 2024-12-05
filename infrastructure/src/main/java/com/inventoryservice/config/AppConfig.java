package com.inventoryservice.config;

import com.inventoryservice.adapter.secondary.*;
import com.inventoryservice.dispatcher.CommandQueryBus;
import com.inventoryservice.domain.service.ProductDomainService;
import com.inventoryservice.ports.primary.ManageInventoryUseCase;
import com.inventoryservice.ports.secondary.InventoryService;
import com.inventoryservice.ports.secondary.NotificationService;
import com.inventoryservice.ports.secondary.ProductRepository;
import com.inventoryservice.services.ManageInventoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

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
