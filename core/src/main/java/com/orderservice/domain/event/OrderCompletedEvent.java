package com.orderservice.domain.event;

public record OrderCompletedEvent(String orderId, String productId, int quantity) {
}
