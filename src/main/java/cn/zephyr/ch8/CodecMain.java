package cn.zephyr.ch8;

import io.netty.buffer.ByteBuf;

/**
 * @ClassName CodecMain
 * @Author laizonghao
 * @CreateTime 2023/1/11 23:31
 * @Description
 **/
public class CodecMain {
    public static void main(String[] args) {
        // 创建业务数据
        LoginRequestPacket requestPacket = new LoginRequestPacket(1,"zhangsan","123456");
        // 创建编解码器对象
        PacketCodeC packetCodeC = new PacketCodeC(SerializerEnum.JSON_SERIALIZER);
        // 编码
        ByteBuf encode = packetCodeC.encode(requestPacket, SerializerEnum.JSON_SERIALIZER.getAlgCode());
        // 解码
        packetCodeC.decode(encode);

    }
}
