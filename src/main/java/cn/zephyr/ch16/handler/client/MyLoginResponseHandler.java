package cn.zephyr.ch16.handler.client;

import cn.zephyr.ch16.common.MySession;
import cn.zephyr.ch16.common.MySessionUtil;
import cn.zephyr.ch16.packet.MyLoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName MyLoginResponseHandler
 * @Author laizonghao
 * @CreateTime 2023/2/6 15:46
 * @Description
 **/
public class MyLoginResponseHandler extends SimpleChannelInboundHandler<MyLoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyLoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("[" + msg.getUserId()+" "+ msg.getUsername() +"] 登录成功");
            MySessionUtil.bindSession(new MySession(msg.getUserId(),msg.getUsername()),ctx.channel());
            ctx.pipeline().remove(this);
        }else {
            // 登录失败时，不会有 userId
            System.err.println("[" + msg.getUsername() +"] 登录失败，错误信息："+msg.getMsg());
        }
    }
}
