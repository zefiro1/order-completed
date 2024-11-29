package com.ordercompleted.domain.model;

import lombok.Getter;

@Getter
public class Order {
  private final String id;
  private String status;

  public Order(String id, String status) {
    this.id = id;
    this.status = "CREATED";
  }

  public void complete(){
    if (!"CREATED".equals(status)){
      throw new IllegalStateException("Cannot complete an order that is not in CREATED state");
    }
    this.status = "COMPLETED";
  }
}
