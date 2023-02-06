package cn.zephyr.ch16.packet;

import cn.zephyr.ch16.common.MyCommandEnum;
import cn.zephyr.ch8.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MyMessageResponsePacket
 * @Author laizonghao
 * @CreateTime 2023/2/3 23:37
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyMessageResponsePacket extends Packet {

    private String fromUserId;
    private String fromUsername;
    private String message;


    @Override
    public Byte getCommand() {
        return MyCommandEnum.MSG_RESPONSE.getCode();
    }

    @Override
    public Class<? extends Packet> getCommandType() {
        return this.getClass();
    }
}
