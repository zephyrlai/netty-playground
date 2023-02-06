package cn.zephyr.ch16;

import cn.zephyr.ch16.handler.server.MyLoginRequestHandler;
import cn.zephyr.ch16.handler.server.MyMessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName Ch16NettyServer
 * @Author laizonghao
 * @CreateTime 2023/2/3 22:16
 * @Description
 **/
public class Ch16NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new MyLoginRequestHandler());
                        pipeline.addLast(new MyMessageRequestHandler());
                    }
                }).bind(8080);
    }
}
