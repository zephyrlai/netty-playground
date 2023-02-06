package cn.zephyr.ch16.handler.server;

import cn.zephyr.ch16.common.MySession;
import cn.zephyr.ch16.common.MySessionUtil;
import cn.zephyr.ch16.packet.MyMessageRequestPacket;
import cn.zephyr.ch16.packet.MyMessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyMessageRequestHandler
 * @Author laizonghao
 * @CreateTime 2023/2/3 23:37
 * @Description
 **/
public class MyMessageRequestHandler extends SimpleChannelInboundHandler<MyMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageRequestPacket msg) throws Exception {
        // 获取发送方 session
        MySession session = MySessionUtil.getSession(ctx.channel());
        // 构建需要转发的消息
        MyMessageResponsePacket responsePacket = new MyMessageResponsePacket(session.getUserId(),session.getUsername(),msg.getMessage());
        // 获取接收方 channel
        Channel clientChannel = MySessionUtil.getChannel(msg.getToUserId());
        // 如果 channel 存在就发送消息，否则不发
        if (clientChannel != null) {
            clientChannel.writeAndFlush(responsePacket);
        }else {
            System.err.println(msg.getToUserId() + "的客户端不在线。");
        }
    }
}
