package com.orderservice.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductResponseMessage {
    private String productId;
    private double price;
}
