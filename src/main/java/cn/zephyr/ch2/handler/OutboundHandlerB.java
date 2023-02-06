package cn.zephyr.ch2.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @ClassName OutboundHandlerA
 * @Author laizonghao
 * @CreateTime 2023/1/13 21:24
 * @Description
 **/
public class OutboundHandlerB extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.err.println(this.getClass().getSimpleName() + " write: " + msg);
        super.write(ctx, msg, promise);
    }
}
