package com.inventoryservice.ports.secondary;


import com.inventoryservice.domain.event.ProductRequestMessage;
import com.inventoryservice.domain.event.ProductResponseMessage;

public interface ProductPriceEventProcessor {
  ProductResponseMessage getProductPrice(ProductRequestMessage request);
}
