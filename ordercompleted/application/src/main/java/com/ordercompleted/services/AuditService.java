package com.ordercompleted.services;

import com.ordercompleted.domain.model.AuditLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Getter
public class AuditService {
  private final List<AuditLog> auditLogs = new ArrayList<>();

  public void log(String operation, String userId, String details, LocalDateTime localDateTime) {
    log.info("Audit Log - Operation: {}, User: {}, Details: {}, Timestamp: {}", operation, userId, details, localDateTime);

    AuditLog log = new AuditLog(operation, userId, details, localDateTime);
    auditLogs.add(log);
  }
}
