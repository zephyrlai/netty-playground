package cn.zephyr.ch16;

import cn.zephyr.ch16.handler.client.MyMessageResponseHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @ClassName Ch16NettyClient
 * @Author laizonghao
 * @CreateTime 2023/2/3 22:15
 * @Description
 **/
public class Ch16NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();
        Bootstrap clientBootstrap = new Bootstrap();
        ChannelFuture channelFuture = clientBootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new MyMessageResponseHandler());
                    }
                }).connect("127.0.0.1", 8080);

    }
}
