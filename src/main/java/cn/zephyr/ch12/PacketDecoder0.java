package cn.zephyr.ch12;

import cn.zephyr.ch8.Packet;
import cn.zephyr.ch8.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName PacketDecoder0
 * @Author laizonghao
 * @CreateTime 2023/1/30 23:57
 * @Description
 **/
public class PacketDecoder0 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        Packet packet = new PacketCodeC().decode(buf);
        ctx.fireChannelRead(packet);
    }
}
