package cn.zephyr.ch8;

/**
 * @ClassName Serializer
 * @Author laizonghao
 * @CreateTime 2023/1/10 23:17
 * @Description
 **/
public interface Serializer {

    byte getSerializerAlgorithm();

    byte[] serialize(Object src);

    <T> T deserialize(byte[] srcBytes,Class<T> desClass);
}
