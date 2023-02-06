package cn.zephyr.ch13;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName MyServerHandler
 * @Author laizonghao
 * @CreateTime 2023/1/30 21:18
 * @Description
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        System.err.println(DateUtil.now() + " server read: " + buf.toString(StandardCharsets.UTF_8));
    }
}

/*public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.err.println(DateUtil.now() + " server read: " + s);
    }
}*/
