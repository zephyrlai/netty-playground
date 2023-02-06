package cn.zephyr.ch16.handler.common;

import cn.zephyr.ch16.common.MyPacketCodeC;
import cn.zephyr.ch8.Packet;
import cn.zephyr.ch8.SerializerEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName MyPacketDecoder
 * @Author laizonghao
 * @CreateTime 2023/2/6 14:33
 * @Description
 **/
public class MyPacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        MyPacketCodeC.getInstance().encode(out,packet, SerializerEnum.JSON_SERIALIZER.getAlgCode());
    }
}
