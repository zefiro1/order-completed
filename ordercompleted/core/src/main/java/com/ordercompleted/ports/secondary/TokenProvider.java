package com.ordercompleted.ports.secondary;

import com.ordercompleted.domain.model.User;

public interface TokenProvider {
  String generateToken(User user);

  boolean validateToken(String token);

  String extractUserId(String token);
}
