package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.model.User;

public interface UserRepository {
  void save(User user);

  User findByEmail(String email);

  User findById(String id);
}
