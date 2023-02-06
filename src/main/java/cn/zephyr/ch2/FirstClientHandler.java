package cn.zephyr.ch2;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName FirstClientHandler
 * @Author laizonghao
 * @CreateTime 2023/1/8 23:23
 * @Description
 **/
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "Hello netty server.";
        System.out.printf("%s:客户端发送数据:%s \n", DateUtil.now(),msg);
        ByteBuf byteBuf = getByteBuf(ctx, msg);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.err.println(DateUtil.now()+": 客户端读取到的数据："+byteBuf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx,String msg) {
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(msg.getBytes(StandardCharsets.UTF_8));
        return buffer;
    }
}
