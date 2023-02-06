package cn.zephyr.ch14;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName MyClientHandler
 * @Author laizonghao
 * @CreateTime 2023/1/30 21:20
 * @Description
 **/
public class MyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg="这里是客户端。";
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(byteBuf);
    }
}
