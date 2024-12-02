package com.ordercompleted.handlers.query;

public interface QueryHandler<Q, R> {
  R handle(Q query);
}

