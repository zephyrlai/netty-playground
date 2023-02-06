package cn.zephyr.ch16.packet;

import cn.zephyr.ch8.Packet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cn.zephyr.ch16.common.MyCommandEnum.LOGIN_RESPONSE;

/**
 * @ClassName MyLoginResponsePacket
 * @Author laizonghao
 * @CreateTime 2023/2/3 23:20
 * @Description
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyLoginResponsePacket extends Packet {
    private boolean success;
    private String userId;
    private String username;
    private String msg;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE.getCode();
    }

    @Override
    public Class<? extends Packet> getCommandType() {
        return this.getClass();
    }
}
