package com.moxuanran.learning.server.demo;

import com.moxuanran.learning.client.EchoClient;
import com.moxuanran.learning.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty客户端连接池资源泄露问题
 *
 * @author wutao
 * @date 2023/3/15 10:51
 */
public class Chapter2 {
    private final String host;
    private final int port;

    public Chapter2(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public void start() throws Exception {
        int poolSize = 100;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();                //1
            b.group(group)                                //2
                    .channel(NioSocketChannel.class)            //3
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {    //5
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });

            for (int i = 0; i < poolSize; i++) {
                b.connect(host,port).sync();
            }
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port = 9999;

        new EchoClient(host, port).start();
    }
}
