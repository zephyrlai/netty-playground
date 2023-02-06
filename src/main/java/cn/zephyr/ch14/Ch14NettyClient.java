package cn.zephyr.ch14;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName Ch13NettyClient
 * @Author laizonghao
 * @CreateTime 2023/1/30 21:22
 * @Description
 **/
public class Ch14NettyClient {
    public static void main(String[] args) {
        Bootstrap client = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        client.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyClientHandler());
                    }
                })
                .connect("127.0.0.1",8000);
    }
}
