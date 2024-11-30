package com.ordercompleted.handlers.query.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetUserByEmailQuery {
  private final String email;
}
