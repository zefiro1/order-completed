package com.orderservice.adapter.primary;

import com.orderservice.ports.primary.OrderServiceUseCase;
import com.orderservice.services.AuditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderServiceUseCase orderServiceUseCase;
    private final AuditService auditService;

    @PostMapping("/create/{orderId}")
    public void createOrder(@PathVariable("orderId") String orderId, @RequestParam String userId) {
        orderServiceUseCase.createOrder(orderId, userId);
        auditService.log("Order Created", userId, "Order ID: %s".formatted(orderId), LocalDateTime.now());
    }

    @PutMapping("/add-item/{orderId}")
    public void addItemToOrder(@PathVariable("orderId") String orderId, @RequestParam String productId, @RequestParam int quantity) {
        orderServiceUseCase.addItemToOrder(orderId, productId, quantity);
    }

    @PutMapping("/update-item-quantity/{orderId}")
    public void updateItemQuantityInOrder(@PathVariable("orderId") String orderId, @RequestParam String productId, @RequestParam int newQuantity) {
        orderServiceUseCase.updateItemQuantityInOrder(orderId, productId, newQuantity);
    }

    @DeleteMapping("/remove-item-quantity/{orderId}")
    public void removeItemFromOrder(@PathVariable("orderId") String orderId, @RequestParam String productId) {
        orderServiceUseCase.removeItemFromOrder(orderId, productId);
    }

    @PostMapping("/cancel/{orderId}")
    public void cancelOrder(@PathVariable("orderId") String orderId) {
        orderServiceUseCase.cancelOrder(orderId);
        auditService.log("Order Canceled", "User", "Order ID: %s".formatted(orderId), LocalDateTime.now());
    }

    @PostMapping("/confirm-payment/{orderId}")
    public void confirmOrderPayment(@PathVariable("orderId") String orderId) {
        orderServiceUseCase.markOrderAsPaid(orderId);
    }

    @PostMapping("/shipped/{orderId}")
    public void markOrderAsShipped(@PathVariable("orderId") String orderId) {
        orderServiceUseCase.markOrderAsShipped(orderId);
    }

    @PostMapping("/completed/{orderId}")
    public void markOrderAsCompleted(@PathVariable("orderId") String orderId) {
        orderServiceUseCase.markOrderAsCompleted(orderId);
    }
}
