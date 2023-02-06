package cn.zephyr.ch2.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * @ClassName OutboundHandlerA
 * @Author laizonghao
 * @CreateTime 2023/1/13 21:24
 * @Description
 **/
public class OutboundHandlerC  extends ChannelOutboundHandlerAdapter {
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush("ChannelOutboundHandlerAdapter.read 发来一条消息\r\n");
        super.read(ctx);
    }
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.err.println(this.getClass().getSimpleName() + " write: " + msg);
        super.write(ctx, msg, promise);
    }
}
