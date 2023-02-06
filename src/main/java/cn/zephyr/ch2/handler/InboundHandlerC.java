package cn.zephyr.ch2.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName InboundHandlerA
 * @Author laizonghao
 * @CreateTime 2023/1/13 21:13
 * @Description
 **/
public class InboundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println(this.getClass().getSimpleName()+" read :"+msg);
        super.channelRead(ctx, msg);
        //服务端channel调用写入方法，出站处理器才会生效
        ctx.channel().writeAndFlush(ctx.alloc().buffer().writeBytes(msg.toString().getBytes()));
    }
}
