package com.ordercompleted.adapter.secondary;

import com.ordercompleted.domain.model.User;
import com.ordercompleted.ports.secondary.TokenProvider;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class JwtTokenProvider implements TokenProvider {
  private final SecretKey key = Jwts.SIG.HS256.key().build();

  @Override
  public String generateToken(User user) {
    String email = user.getEmail();
    return Jwts.builder().claim("ROLE", user.getRole()).subject(email).signWith(key).compact();
  }

  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }

  @Override
  public String extractUserId(String token) {
    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
  }
}
