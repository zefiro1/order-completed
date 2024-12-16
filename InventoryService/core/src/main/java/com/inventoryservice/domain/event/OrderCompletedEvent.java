package com.inventoryservice.domain.event;

public record OrderCompletedEvent(String orderId, String productId, int quantity) {
}
