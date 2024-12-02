package com.ordercompleted.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ProductSalesReport {
  private String productId;
  private int salesCount;
}
