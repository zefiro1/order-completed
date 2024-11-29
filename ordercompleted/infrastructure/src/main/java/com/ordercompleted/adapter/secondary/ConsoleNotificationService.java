package com.ordercompleted.adapter.secondary;

import com.ordercompleted.ports.secondary.NotificationService;

public class ConsoleNotificationService implements NotificationService {
  @Override
  public void sendNotification(String message) {
    System.out.println("NOTIFICACIÓN: " + message);
  }
}
