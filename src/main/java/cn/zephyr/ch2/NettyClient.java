package cn.zephyr.ch2;

import cn.hutool.core.date.DateUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName NettyClient
 * @Author laizonghao
 * @CreateTime 2023/1/7 22:03
 * @Description
 **/
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new FirstClientHandler());
                    }
                });
        autoConnect(bootstrap,"127.0.0.1",8000,1);

    }

    public static void autoConnect(Bootstrap bootstrap,String host,int port){
        bootstrap.connect(host,port).addListener(future -> {
            if (future.isSuccess()) {
                System.err.println("连接成功");
            }else {
                System.err.println("连接失败，尝试重连");
                autoConnect(bootstrap, host, port);
            }
        });
    }
    public static final int MAX_RETRY = 5;

    /**
     * 失败自动重连
     * @param bootstrap
     * @param host 服务端地址
     * @param port 端口
     * @param retryCount 目前处于第几次重连
     */
    public static void autoConnect(Bootstrap bootstrap,String host,int port,int retryCount){
        bootstrap.connect(host,port).addListener(future -> {
            if (future.isSuccess()) {
                System.err.println("连接成功");
            } else if(retryCount == MAX_RETRY){
                System.err.printf(" %s : 第 %s 次连接失败,重次次数已达上限，放弃连接。%n",DateUtil.now(),retryCount);
            }else {
                System.err.printf(" %s : 第 %s 次连接失败，尝试重连%n",DateUtil.now(),retryCount);
                int delay = 1 << retryCount;
                bootstrap.config().group().schedule(
                        () -> autoConnect(bootstrap, host, port,retryCount+1),
                        delay,
                        TimeUnit.SECONDS);
            }
        });
    }
}
