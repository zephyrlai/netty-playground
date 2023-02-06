package cn.zephyr.ch8;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PacketCodeC
 * @Author laizonghao
 * @CreateTime 2023/1/10 23:26
 * @Description
 **/
@Data
public class PacketCodeC {

    private Map<Byte,Serializer> serializerMap;


    public static final int MAGIC_NUMBER = 0x12345678;

    public PacketCodeC(SerializerEnum... serializerEnums) {
        if (serializerEnums.length>0) {
            try {
                Map<Byte, Serializer> serializerMap = new HashMap<>();
                for (SerializerEnum serializerEnum : serializerEnums) {
                    serializerMap.put(serializerEnum.getAlgCode(),serializerEnum.getClazz().newInstance());
                }
                this.serializerMap = serializerMap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ByteBuf encode(Packet packet, Byte serializeAlg){
        // 创建 ByteBuf 容器
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        // 序列化业务数据
        Serializer serializer = serializerMap.get(serializeAlg);
        if (serializer == null) {
            throw new RuntimeException("Serializer not exist:"+serializeAlg);
        }
        byte[] bytes = serializer.serialize(packet);
        // 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 获取反序列化算法
        byte serializeAlg = byteBuf.readByte();
        // 获取指令
        byte command = byteBuf.readByte();
        // 获取数据长度
        int length = byteBuf.readInt();
        // 获取数据
        byte[] data = new byte[length];
        byteBuf.readBytes(data);

        // 解析指令对应的反序列化类型
        CommandEnum commandEnum = CommandEnum.getCommandEnum(command);
        if (commandEnum == null) {
            throw new RuntimeException(" Command not exist:"+command);
        }
        System.err.printf("=== 解码后的指令类是：%s ===\n", commandEnum.getClazz().getSimpleName());
        // 解析反序列化算法
        Serializer serializer = serializerMap.get(serializeAlg);
        if (serializer == null) {
            throw new RuntimeException("Serializer not exist:"+serializeAlg);
        }
        System.err.printf("=== 解码后的反序列化算法类是：%s ===\n", serializer.getClass().getSimpleName());

        // 反序列化数据
        Packet packet = serializer.deserialize(data, commandEnum.getClazz());
        System.err.printf("=== 反序列化后的数据为：%s ===\n", JSON.toJSON(packet));
        return packet;
    }
}
