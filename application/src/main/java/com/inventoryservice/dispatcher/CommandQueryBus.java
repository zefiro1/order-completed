package com.inventoryservice.dispatcher;


import com.inventoryservice.handlers.command.CommandHandler;
import com.inventoryservice.handlers.query.QueryHandler;

public class CommandQueryBus {
  public <T> void dispatchCommand(T command, CommandHandler<T> handler) {
    handler.handle(command);
  }

  public <Q, R> R dispatchQuery(Q query, QueryHandler<Q, R> handler) {
    return handler.handle(query);
  }
}
