package com.ordercompleted.handlers.query;

public interface QueryHandler<R> {
  R handle(Object query);
}

