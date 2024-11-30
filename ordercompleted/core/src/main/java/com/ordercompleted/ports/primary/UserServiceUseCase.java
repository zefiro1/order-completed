package com.ordercompleted.ports.primary;

import com.ordercompleted.domain.model.Role;
import com.ordercompleted.domain.model.User;

public interface UserServiceUseCase {
  void registerUser(String email, String password, String name, Role role);

  String loginUser(String email, String password);

  void checkAdminAccess(User user);
}
