package com.ordercompleted.adapter.primary;

import com.ordercompleted.domain.model.Role;
import com.ordercompleted.domain.model.User;
import com.ordercompleted.ports.primary.UserServiceUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserController {
  private final UserServiceUseCase userServiceUseCase;

  public void register(String email, String password, String name, Role role) {
    userServiceUseCase.registerUser(email, password, name, role);
  }

  public String login(String email, String password) {
    return userServiceUseCase.loginUser(email, password);
  }

  public void checkAdminAccess(User user) {
    userServiceUseCase.checkAdminAccess(user);
  }
}
