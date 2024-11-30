package com.ordercompleted.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
  private String id;
  private String email;
  private String password;
  private String name;
  private Role role;

  public boolean hasRole(Role role) {
    return this.role == role;
  }
}
