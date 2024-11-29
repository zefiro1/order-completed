package com.ordercompleted.handlers.command.product;

import com.ordercompleted.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddProductCommand {
  private final Product product;
}
