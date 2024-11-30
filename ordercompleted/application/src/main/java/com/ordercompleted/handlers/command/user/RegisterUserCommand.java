package com.ordercompleted.handlers.command.user;

import com.ordercompleted.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserCommand {
  private final User user;
}
