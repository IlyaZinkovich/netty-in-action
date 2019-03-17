package io.github.ilyazinkovich.netty.ch2.client;

public class EchoClientApp {

  private static final String DEFAULT_HOST = "localhost";
  private static final int DEFAULT_PORT = 8888;

  public static void main(String[] args) throws InterruptedException {
    final String host;
    final int port;
    if (args.length == 2) {
      host = args[0];
      port = Integer.parseInt(args[1]);
    } else {
      host = DEFAULT_HOST;
      port = DEFAULT_PORT;
    }
    System.out.printf("Starting client on port %d%n", port);
    new EchoClientBootstrap(host, port).start();
  }
}
