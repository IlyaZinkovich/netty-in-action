package io.github.ilyazinkovich.netty.ch2.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.net.InetSocketAddress;

public class EchoServerBootstrap {

  private final int port;

  public EchoServerBootstrap(final int port) {
    this.port = port;
  }

  public void start() throws InterruptedException {
    final EchoServerHandler serverHandler = new EchoServerHandler();
    final EventLoopGroup group = new NioEventLoopGroup();
    try {
      final ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(group)
          .channel(NioServerSocketChannel.class)
          .localAddress(new InetSocketAddress(port))
          .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(final SocketChannel channel) {
              channel.pipeline().addLast(serverHandler);
            }
          });
      final ChannelFuture bindFuture = bootstrap.bind().sync();
      bindFuture.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully().sync();
    }
  }
}
