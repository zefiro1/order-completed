package com.inventoryservice.adapter.secondary;


import com.inventoryservice.domain.event.ProductRequestMessage;
import com.inventoryservice.domain.event.ProductResponseMessage;
import com.inventoryservice.domain.model.Product;
import com.inventoryservice.ports.secondary.ProductPriceEventProcessor;
import com.inventoryservice.ports.secondary.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderEventConsumer implements ProductPriceEventProcessor {
    private final ProductRepository productRepository;
    @RabbitListener(queues = "product.request.queue")
    public ProductResponseMessage getProductPrice(ProductRequestMessage request) {
        Product product = productRepository.findById(request.getProductId());
        return new ProductResponseMessage(product.getId(), product.getPrice());
    }
}
