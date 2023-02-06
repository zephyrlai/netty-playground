package cn.zephyr.ch2;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName FirstServerHandler
 * @Author laizonghao
 * @CreateTime 2023/1/8 21:36
 * @Description
 **/
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.err.println(DateUtil.now()+": 服务端读取到的数据："+byteBuf.toString(StandardCharsets.UTF_8));
        String serverMsg = "Hello netty client.";
        ctx.channel().writeAndFlush(getByteBuf(ctx,serverMsg));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String msg) {
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
        return buffer;
    }
}
