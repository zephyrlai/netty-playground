package cn.zephyr.ch16;

import cn.zephyr.ch16.common.MySessionUtil;
import cn.zephyr.ch16.handler.client.MyLoginResponseHandler;
import cn.zephyr.ch16.handler.client.MyMessageResponseHandler;
import cn.zephyr.ch16.handler.common.MyPacketDecoder;
import cn.zephyr.ch16.handler.common.MyPacketEncoder;
import cn.zephyr.ch16.handler.common.ProtocolCheckDecoder;
import cn.zephyr.ch16.packet.MyLoginRequestPacket;
import cn.zephyr.ch16.packet.MyMessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
        clientBootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // decoder
                        pipeline.addLast(new ProtocolCheckDecoder());
                        pipeline.addLast(new MyPacketDecoder());
                        // inboundHandler
                        pipeline.addLast(new MyLoginResponseHandler());
                        pipeline.addLast(new MyMessageResponseHandler());
                        // encoder
                        pipeline.addLast(new MyPacketEncoder());
                    }
                });
        connect(clientBootstrap,"127.0.0.1",8080,5);
    }

        private static void connect(Bootstrap bootstrap ,String host, int port, int retry) {
            bootstrap.connect(host, port).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                    Channel channel = ((ChannelFuture) future).channel();

                    //开始建立消息通信
                    startConsoleThread(channel);
                } else if (retry == 0) {
                    System.err.println("重试次数已用完，放弃连接！");
                } else {
                    // 第几次重连
                    int order = (5 - retry) + 1;
                    // 本次重连的间隔
                    int delay = 1 << order;
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                    bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                            .SECONDS);
                }
            });
        }

        private static void startConsoleThread(Channel channel) {
            Scanner sc = new Scanner(System.in);

            new Thread(() -> {
                MyLoginRequestPacket loginRequestPacket = new MyLoginRequestPacket();
                while (!Thread.interrupted()) {
                    if (!MySessionUtil.hasLogin(channel)) {
                        System.out.print("输入用户名登录: ");
                        String username = sc.nextLine();
                        loginRequestPacket.setUsername(username);

                        // 密码使用默认的
                        loginRequestPacket.setPassword("123456");

                        // 发送登录数据包
                        channel.writeAndFlush(loginRequestPacket);
                        waitForLoginResponse();
                    } else {
                        System.out.println("请输入接收用户的ID:");
                        String toUserId = sc.next();
                        System.out.println("请输入消息内容:");
                        String message = sc.next();
                        channel.writeAndFlush(new MyMessageRequestPacket(toUserId, message));
                    }
                }
            }).start();
        }

        private static void waitForLoginResponse() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
}
