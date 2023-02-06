package cn.zephyr.ch12;

import cn.zephyr.ch8.Packet;
import cn.zephyr.ch12.PacketCodeC;
import cn.zephyr.ch8.SerializerEnum;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName PacketEncoder
 * @Author laizonghao
 * @CreateTime 2023/1/30 23:57
 * @Description
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf out) throws Exception {
        new PacketCodeC().encode(out,packet, SerializerEnum.JSON_SERIALIZER.getAlgCode());
    }
}
