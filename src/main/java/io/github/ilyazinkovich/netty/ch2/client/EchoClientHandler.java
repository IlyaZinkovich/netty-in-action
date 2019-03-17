package io.github.ilyazinkovich.netty.ch2.client;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

  @Override
  public void channelActive(final ChannelHandlerContext ctx) {
    ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", UTF_8));
  }

  @Override
  protected void channelRead0(final ChannelHandlerContext ctx, final ByteBuf msg) {
    System.out.printf("Client received: %s%n", msg.toString(UTF_8));
  }

  @Override
  public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
