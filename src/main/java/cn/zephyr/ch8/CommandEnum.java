package cn.zephyr.ch8;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName CommandEnum
 * @Author laizonghao
 * @CreateTime 2023/1/11 23:20
 * @Description
 **/
@AllArgsConstructor
@Getter
public enum CommandEnum {
    LOGIN_REQUEST((byte)1, LoginRequestPacket.class,"登录指令"),
    ;
    private byte code;
    private Class<? extends Packet> clazz;
    private String desc;

    public static CommandEnum getCommandEnum(byte code) {
        CommandEnum[] values = CommandEnum.values();
        for (CommandEnum command : values) {
            if (command.code==code) {
                return command;
            }
        }
        return null;
    }

}
