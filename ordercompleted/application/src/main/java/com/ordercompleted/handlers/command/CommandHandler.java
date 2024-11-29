package com.ordercompleted.handlers.command;

public interface CommandHandler<T> {
  void handle(T command);
}

