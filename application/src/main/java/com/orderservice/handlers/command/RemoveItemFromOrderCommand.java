package com.orderservice.handlers.command;


public record RemoveItemFromOrderCommand(String orderId, String productId) {
}
