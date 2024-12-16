package com.orderservice.domain.service;

import com.orderservice.domain.event.ProductRequestMessage;
import com.orderservice.domain.event.ProductResponseMessage;
import com.orderservice.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderDomainService {
     private final AmqpTemplate amqpTemplate;

    public double calculateTotalAmount(Order order) {
        List<ProductResponseMessage> productResponses = order.getItems().keySet().stream()
                .map(orderItem -> {
                    ProductRequestMessage request = new ProductRequestMessage(orderItem);
                    return (ProductResponseMessage) amqpTemplate.convertSendAndReceive("inventory.exchange", "product.routing.key", request);
                })
                .toList();

        double totalAmount = 0;
        for (ProductResponseMessage response : productResponses) {
            double productPrice = response.getPrice();
            int quantity = order.getItems().get(response.getProductId()).getQuantity();
            totalAmount += productPrice * quantity;
        }
        return totalAmount;
    }

}
