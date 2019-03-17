package io.github.ilyazinkovich.netty.ch2.server;

public class EchoServerApp {

  private static final int DEFAULT_PORT = 8888;

  public static void main(String[] args) throws InterruptedException {
    final int port;
    if (args.length == 1) {
      port = Integer.parseInt(args[0]);
    } else {
      port = DEFAULT_PORT;
    }
    System.out.printf("Starting server on port %d%n", port);
    new EchoServerBootstrap(port).start();
  }
}
