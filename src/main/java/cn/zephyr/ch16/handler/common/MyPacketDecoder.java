package cn.zephyr.ch16.handler.common;

import cn.zephyr.ch16.common.MyPacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName MyPacketDecoder
 * @Author laizonghao
 * @CreateTime 2023/2/6 14:33
 * @Description
 **/
public class MyPacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // ByteBuf -> Packet
        out.add(MyPacketCodeC.getInstance().decode(in));
    }
}
