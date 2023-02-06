package cn.zephyr.ch12;

import cn.zephyr.ch8.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName LoginRequestHandler
 * @Author laizonghao
 * @CreateTime 2023/1/30 23:27
 * @Description
 **/
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        // 登录逻辑
    }
}
