package com.inventoryservice.handlers.command;

import com.inventoryservice.domain.service.ProductDomainService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProductStockCommandHandler implements CommandHandler<UpdateProductStockCommand> {
    private final ProductDomainService productDomainService;

    @Override
    public void handle(UpdateProductStockCommand command) {
        productDomainService.reduceStock(command.getProductId(), command.getNewStock());
    }
}
