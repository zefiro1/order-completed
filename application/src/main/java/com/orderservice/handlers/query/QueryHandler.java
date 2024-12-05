package com.orderservice.handlers.query;

public interface QueryHandler<Q, R> {
    R handle(Q query);
}

