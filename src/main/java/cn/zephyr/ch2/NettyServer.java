package cn.zephyr.ch2;

import cn.zephyr.ch2.handler.InboundHandlerA;
import cn.zephyr.ch2.handler.InboundHandlerB;
import cn.zephyr.ch2.handler.InboundHandlerC;
import cn.zephyr.ch2.handler.OutboundHandlerA;
import cn.zephyr.ch2.handler.OutboundHandlerB;
import cn.zephyr.ch2.handler.OutboundHandlerC;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @ClassName NettyServer
 * @Author laizonghao
 * @CreateTime 2023/1/7 23:13
 * @Description
 **/
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap
            .group(bossGroup,workerGroup)
            .channel(NioServerSocketChannel.class)
            .attr(AttributeKey.newInstance("serverName"),"hahaServer")
            /*.handler(new ChannelInitializer<NioServerSocketChannel>() {
                @Override
                protected void initChannel(NioServerSocketChannel nioSocketChannel) throws Exception {
                    System.err.println("=== 服务端正在启动... ===");
                }
            })*/
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(new StringDecoder());
//                    ch.pipeline().addLast(new FirstServerHandler());
//
                    // inbound
                    ch.pipeline().addLast(new InboundHandlerA());
                    ch.pipeline().addLast(new InboundHandlerB());
                    ch.pipeline().addLast(new InboundHandlerC());
                    // outbound
                    ch.pipeline().addLast(new OutboundHandlerA());
                    ch.pipeline().addLast(new OutboundHandlerB());
                    ch.pipeline().addLast(new OutboundHandlerC());


//                    ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                        @Override
//                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//                            System.err.println(s);
//                        }
//                    });
                }
            })
//            .bind(8000)
            ;
        autoBind(serverBootstrap,8000);
    }

    public static void autoBind(ServerBootstrap serverBootstrap,int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                System.err.println("尝试绑定端口："+port);
                if (future.isSuccess()) {
                    System.err.println("成功绑定端口："+port);
                }else {
                    // 递归
                    autoBind(serverBootstrap,port+1);
                }
            }
        });
    }
}
