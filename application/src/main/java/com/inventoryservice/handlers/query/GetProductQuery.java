package com.inventoryservice.handlers.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetProductQuery {
  private final String productId;
}
