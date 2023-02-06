package cn.zephyr.ch16.handler.client;

import cn.hutool.core.date.DateUtil;
import cn.zephyr.ch16.packet.MyMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyMessageResponseHandler
 * @Author laizonghao
 * @CreateTime 2023/2/3 22:14
 * @Description
 **/
public class MyMessageResponseHandler extends SimpleChannelInboundHandler<MyMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageResponsePacket msg) throws Exception {
        System.err.println(DateUtil.now()+" "+msg.getFromUserId()+" "+ msg.getFromUsername()+": "+msg.getMessage());
    }
}
