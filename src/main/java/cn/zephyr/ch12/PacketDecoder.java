package cn.zephyr.ch12;

import cn.zephyr.ch8.Packet;
import cn.zephyr.ch8.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName PacketDecoder
 * @Author laizonghao
 * @CreateTime 2023/1/30 23:57
 * @Description
 **/
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        out.add(new PacketCodeC().decode(byteBuf));
    }
}
