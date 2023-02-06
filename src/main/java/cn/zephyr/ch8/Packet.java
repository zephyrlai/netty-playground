package cn.zephyr.ch8;

import lombok.Data;

/**
 * @ClassName Packet
 * @Author laizonghao
 * @CreateTime 2023/1/10 23:04
 * @Description
 **/
@Data
public abstract class Packet {

    public static final Byte LOGIN_REQUEST=1;

    /**
     * 协议版本
     */
    private Byte version=1;

    /**
     * 获取指令
     * @return
     */
    public abstract Byte getCommand();

    public abstract Class<? extends Packet> getCommandType();

}
