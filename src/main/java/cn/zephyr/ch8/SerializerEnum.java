package cn.zephyr.ch8;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName SerializerEnum
 * @Author laizonghao
 * @CreateTime 2023/1/11 23:20
 * @Description
 **/
@AllArgsConstructor
@Getter
public enum SerializerEnum {
    JSON_SERIALIZER((byte)1, JsonSerializer.class,"JSON"),
    ;
    private byte algCode;
    private Class<? extends Serializer> clazz;
    private String desc;
}
