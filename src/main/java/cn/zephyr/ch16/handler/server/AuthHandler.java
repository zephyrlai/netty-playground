package cn.zephyr.ch16.handler.server;

import cn.zephyr.ch16.common.MySessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName AuthHandler
 * @Author laizonghao
 * @CreateTime 2023/2/6 14:47
 * @Description 客户端已登录，则从该channel 上移除 pipeline 中的 AuthHandler，否则关闭连接
 **/
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!MySessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().closeFuture();
        }else{
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
