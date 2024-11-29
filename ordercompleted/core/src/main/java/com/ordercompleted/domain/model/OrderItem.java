package com.ordercompleted.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class OrderItem {
  private String productId;
  @Setter
  private int quantity;
}
