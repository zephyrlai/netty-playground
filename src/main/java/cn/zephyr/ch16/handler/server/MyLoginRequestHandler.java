package cn.zephyr.ch16.handler.server;

import cn.zephyr.ch16.common.MySession;
import cn.zephyr.ch16.common.MySessionUtil;
import cn.zephyr.ch16.packet.MyLoginRequestPacket;
import cn.zephyr.ch16.packet.MyLoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @ClassName MyLoginRequestHandler
 * @Author laizonghao
 * @CreateTime 2023/2/3 23:18
 * @Description
 **/
public class MyLoginRequestHandler extends SimpleChannelInboundHandler<MyLoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyLoginRequestPacket msg) throws Exception {
        // 密码不正确，则直接返回
        if (!checkPassword(msg)) {
            MyLoginResponsePacket responsePacket = MyLoginResponsePacket.builder()
                    .msg("密码错误，请重试")
                    .success(false)
                    .username(msg.getUsername())
                    .build();
            ctx.channel().writeAndFlush(responsePacket);
            ctx.channel().closeFuture();
        }
        // 绑定 session
        String userId = UUID.randomUUID().toString().substring(0,5);
        System.err.println("["+userId+" "+msg.getUsername()+"] 登录成功");
        MySession mySession = new MySession(userId, msg.getUsername(), msg.getAge());
        MySessionUtil.bindSession(mySession,ctx.channel());
        // 返回响应
        MyLoginResponsePacket responsePacket = MyLoginResponsePacket.builder()
                .msg("登录成功")
                .success(true)
                .userId(userId)
                .username(msg.getUsername())
                .build();
        ctx.channel().writeAndFlush(responsePacket);
    }

    /**
     * 客户端掉线/下线时，解除绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        MySessionUtil.unbindSession(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    private boolean checkPassword(MyLoginRequestPacket loginRequestPacket) {

        return loginRequestPacket.getPassword().equals("123456");
    }
}
