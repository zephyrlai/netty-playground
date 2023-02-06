package cn.zephyr.ch2.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName InboundHandlerA
 * @Author laizonghao
 * @CreateTime 2023/1/13 21:13
 * @Description
 **/
public class InboundHandlerA extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println(this.getClass().getSimpleName()+" read :"+msg);
        super.channelRead(ctx, msg);
    }
}
