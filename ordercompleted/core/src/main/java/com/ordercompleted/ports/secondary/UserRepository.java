package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.model.User;

import java.util.List;

public interface UserRepository {
  void save(User user);

  User findByEmail(String email);

  User findById(String id);

  List<User> findAll();
}
