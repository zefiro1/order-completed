package com.inventoryservice.handlers.command;

public interface CommandHandler<T> {
    void handle(T command);
}

