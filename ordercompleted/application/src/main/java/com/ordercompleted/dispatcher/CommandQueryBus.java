package com.ordercompleted.dispatcher;

import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.handlers.query.QueryHandler;

public class CommandQueryBus {
  public <T> void dispatchCommand(T command, CommandHandler<T> handler) {
    handler.handle(command);
  }

  public <R> R dispatchQuery(Object query, QueryHandler<R> handler) {
    return handler.handle(query);
  }
}
