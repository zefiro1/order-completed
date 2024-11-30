package com.ordercompleted.handlers.query.user;

import com.ordercompleted.domain.model.User;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserByEmailQueryHandler implements QueryHandler<User> {
  private final UserRepository userRepository;

  @Override
  public User handle(Object query) {
    if (query instanceof GetUserByEmailQuery getUserByEmailQuery) {
      return userRepository.findByEmail(getUserByEmailQuery.getEmail());
    }
    throw new IllegalArgumentException("Invalid query type");
  }
}
