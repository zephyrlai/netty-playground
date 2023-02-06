package cn.zephyr.ch16.packet;

import cn.zephyr.ch8.Packet;
import lombok.Data;

/**
 * @ClassName MyLoginRequestPacket
 * @Author laizonghao
 * @CreateTime 2023/2/3 23:19
 * @Description
 **/
@Data
public class MyLoginRequestPacket extends Packet {

    private String username;
    private String password;
    private int age;


    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    @Override
    public Class<? extends Packet> getCommandType() {
        return this.getClass();
    }
}
