package com.orderservice.handlers.command;

public interface CommandHandler<T> {
    void handle(T command);
}

