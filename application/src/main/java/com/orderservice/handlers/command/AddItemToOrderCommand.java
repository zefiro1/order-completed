package com.orderservice.handlers.command;

public record AddItemToOrderCommand(String orderId, String productId, int quantity) {
}
