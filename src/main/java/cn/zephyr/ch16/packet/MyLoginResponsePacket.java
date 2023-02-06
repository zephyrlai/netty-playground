package cn.zephyr.ch16.packet;

import cn.zephyr.ch8.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MyLoginResponsePacket
 * @Author laizonghao
 * @CreateTime 2023/2/3 23:20
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyLoginResponsePacket extends Packet {

    private String msg;
    @Override
    public Byte getCommand() {
        return null;
    }

    @Override
    public Class<? extends Packet> getCommandType() {
        return null;
    }
}
