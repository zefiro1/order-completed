package com.orderservice.handlers.command;


public record CreateOrderCommand(String orderId, String userId) {
}
