package cn.zephyr.ch16.common;

import cn.zephyr.ch16.packet.MyLoginRequestPacket;
import cn.zephyr.ch16.packet.MyLoginResponsePacket;
import cn.zephyr.ch16.packet.MyMessageRequestPacket;
import cn.zephyr.ch16.packet.MyMessageResponsePacket;
import cn.zephyr.ch8.LoginRequestPacket;
import cn.zephyr.ch8.Packet;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName CommandEnum
 * @Author laizonghao
 * @CreateTime 2023/1/11 16:20
 * @Description
 **/
@AllArgsConstructor
@Getter
public enum MyCommandEnum {
    LOGIN_REQUEST((byte)1, MyLoginRequestPacket.class,"登录请求指令"),
    LOGIN_RESPONSE((byte)2, MyLoginResponsePacket.class,"登录相应指令"),
    MSG_REQUEST((byte)3, MyMessageRequestPacket.class,"消息请求指令"),
    MSG_RESPONSE((byte)4, MyMessageResponsePacket.class,"消息相应指令"),
    ;
    private byte code;
    private Class<? extends Packet> clazz;
    private String desc;

    public static MyCommandEnum getCommandEnum(byte code) {
        MyCommandEnum[] values = MyCommandEnum.values();
        for (MyCommandEnum command : values) {
            if (command.code==code) {
                return command;
            }
        }
        return null;
    }

}
