package com.inventoryservice.domain.event;

import lombok.Builder;

@Builder
public record ProductResponseMessage(String productId, double price) {
}
