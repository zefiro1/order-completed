package com.ordercompleted.ports.secondary;

public interface PasswordEncoder {
  String encode(String rawPassword);

  boolean matches(String rawPassword, String encodedPassword);
}
