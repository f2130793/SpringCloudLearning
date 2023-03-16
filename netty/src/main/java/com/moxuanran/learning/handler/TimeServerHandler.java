package com.moxuanran.learning.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/7 19:51
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        final ByteBuf time = ctx.alloc().buffer(4);
//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//        final ChannelFuture f = ctx.writeAndFlush(time);
//        f.addListener((ChannelFutureListener) channelFuture -> {
//            assert f == channelFuture;
//            ctx.close();
//        });
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("TimeServerHandler received: " + body);
        String currentTime = String.valueOf(System.currentTimeMillis() / 1000L + 2);
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
