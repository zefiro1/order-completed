package com.orderservice.handlers.command;


public record UpdateItemQuantityInOrderCommand(String orderId, String productId, int newQuantity) {
}
