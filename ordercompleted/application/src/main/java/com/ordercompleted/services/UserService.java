package com.ordercompleted.services;

import com.ordercompleted.dispatcher.CommandQueryBus;
import com.ordercompleted.domain.model.Role;
import com.ordercompleted.domain.model.User;
import com.ordercompleted.handlers.command.user.RegisterUserCommand;
import com.ordercompleted.handlers.command.user.RegisterUserCommandHandler;
import com.ordercompleted.handlers.query.user.GetUserByEmailQuery;
import com.ordercompleted.handlers.query.user.GetUserByEmailQueryHandler;
import com.ordercompleted.ports.primary.UserServiceUseCase;
import com.ordercompleted.ports.secondary.PasswordEncoder;
import com.ordercompleted.ports.secondary.TokenProvider;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class UserService implements UserServiceUseCase {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenProvider tokenProvider;
  private final CommandQueryBus commandQueryBus;

  @Override
  public void registerUser(String email, String password, String name, Role role) {
    User existingUser = getUserByEmail(email);
    if (Objects.nonNull(existingUser)) {
      throw new IllegalStateException("El correo ya está registrado.");
    }
    String encodedPassword = passwordEncoder.encode(password);
    User user = new User("1", email, encodedPassword, name, role);
    registerUserInRepository(user);
  }

  @Override
  public String loginUser(String email, String password) {
    User user = getUserByEmail(email);
    if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalStateException("Credenciales inválidas.");
    }
    System.out.printf("User loged: %s%n", email);
    return tokenProvider.generateToken(user);
  }

  @Override
  public void checkAdminAccess(User user) {
    if (!user.hasRole(Role.ADMIN)) {
      throw new IllegalStateException("Acceso denegado: solo los administradores pueden realizar esta acción.");
    }
  }

  @Override
  public List<User> allUsers() {
    return userRepository.findAll();
  }

  private void registerUserInRepository(User user) {
    RegisterUserCommand registerUserCommand = new RegisterUserCommand(user);
    commandQueryBus.dispatchCommand(registerUserCommand, new RegisterUserCommandHandler(userRepository));
    System.out.printf("User Registred: %s%n", user.getEmail());
  }

  private User getUserByEmail(String email) {
    GetUserByEmailQuery getUserByEmailQuery = new GetUserByEmailQuery(email);
    return commandQueryBus.dispatchQuery(getUserByEmailQuery, new GetUserByEmailQueryHandler(userRepository));
  }
}
