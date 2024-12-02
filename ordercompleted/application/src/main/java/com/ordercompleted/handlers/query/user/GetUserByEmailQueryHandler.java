package com.ordercompleted.handlers.query.user;

import com.ordercompleted.domain.model.User;
import com.ordercompleted.handlers.query.QueryHandler;
import com.ordercompleted.ports.secondary.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserByEmailQueryHandler implements QueryHandler<GetUserByEmailQuery, User> {
  private final UserRepository userRepository;

  @Override
  public User handle(GetUserByEmailQuery query) {
    return userRepository.findByEmail(query.getEmail());
  }
}
