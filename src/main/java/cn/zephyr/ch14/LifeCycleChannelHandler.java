package cn.zephyr.ch14;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName LifeCycleChannelHandler
 * @Author laizonghao
 * @CreateTime 2023/2/2 19:08
 * @Description
 **/
public class LifeCycleChannelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" channelRegistered()：channel 被绑定到 NioEventLoop 线程");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" channelUnregistered()：channel 被 NioEventLoop 线程移除");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" channelActive()：channel 就绪");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" channelInactive()：channel 关闭");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.print(DateUtil.now()+" channelRead()：channel 有数据可读，");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.err.println("服务端读取到的数据："+byteBuf.toString(StandardCharsets.UTF_8));
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" channelReadComplete()：channel 某次数据被读完");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" handlerAdded()：handler 被添加");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.err.println(DateUtil.now()+" handlerAdded()：handler 被移除");
        super.handlerRemoved(ctx);
    }
}
