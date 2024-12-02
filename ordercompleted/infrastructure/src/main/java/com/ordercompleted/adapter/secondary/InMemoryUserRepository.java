package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.User;
import com.ordercompleted.ports.secondary.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
  private final Map<String, User> users = new HashMap<>();

  @Override
  public void save(User user) {
    users.put(user.getId(), user);
  }

  @Override
  public User findByEmail(String email) {
    return users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
  }

  @Override
  public User findById(String id) {
    return users.get(id);
  }

  @Override
  public List<User> findAll() {
    return List.copyOf(users.values());
  }
}
