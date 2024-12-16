package com.orderservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class AuditLog {
  private final String operation;
  private final String userId;
  private final String details;
  private final LocalDateTime timestamp;
}
