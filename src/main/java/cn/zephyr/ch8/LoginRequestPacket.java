package cn.zephyr.ch8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginRequestPacket
 * @Author laizonghao
 * @CreateTime 2023/1/10 23:15
 * @Description
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet{

    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    @Override
    public Class<? extends Packet> getCommandType() {
        return this.getClass();
    }
}
