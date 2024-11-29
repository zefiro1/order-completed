package com.filewatcher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.file.*;

public class FileWatcherProducer {
  private static final String QUEUE_NAME = "order_completed_queue";
  private static final String RABBITMQ_HOST = "localhost";
  private static final String USERNAME = "user";
  private static final String PASSWORD = "password";
  private static final String VIRTUAL_HOST = "/";

  private static final String DIRECTORY_TO_WATCH = "C:\\dev\\order-completed\\orders";  // Cambia por el directorio que quieras monitorear

  public static void main(String[] args) throws Exception {
    // Crear conexión con RabbitMQ
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(RABBITMQ_HOST);
    factory.setUsername(USERNAME);
    factory.setPassword(PASSWORD);
    factory.setVirtualHost(VIRTUAL_HOST);


    try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {

      // Declarar una cola en RabbitMQ
      channel.queueDeclare(QUEUE_NAME, true, false, false, null);
      System.out.println(" [*] Observando el directorio: " + DIRECTORY_TO_WATCH);

      // Configurar el WatchService para monitorear el directorio
      Path path = Paths.get(DIRECTORY_TO_WATCH);
      WatchService watchService = FileSystems.getDefault().newWatchService();
      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

      // Monitorizar el directorio para nuevos archivos
      while (true) {
        WatchKey key;
        try {
          key = watchService.take();
        } catch (InterruptedException ex) {
          return;
        }

        // Iterar sobre los eventos de la clave
        for (WatchEvent<?> event : key.pollEvents()) {
          WatchEvent.Kind<?> kind = event.kind();
          if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
            // Si un archivo es agregado
            Path filename = (Path) event.context();
            System.out.println("Nuevo archivo agregado: " + filename);

            // Enviar un mensaje a RabbitMQ notificando que se ha agregado un archivo
            String message = "Nuevo archivo agregado: " + filename.toString();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Enviado: '" + message + "'");
          }
        }

        // Reiniciar la clave
        boolean valid = key.reset();
        if (!valid) {
          break;
        }
      }
    }
  }
}
