package com.ordercompleted.handlers.command.user;

import com.ordercompleted.handlers.command.CommandHandler;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterUserCommandHandler implements CommandHandler<RegisterUserCommand> {
  private final UserRepository userRepository;

  @Override
  public void handle(RegisterUserCommand command) {
    userRepository.save(command.getUser());
  }
}