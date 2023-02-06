package cn.zephyr.ch8;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName JsonSerializer
 * @Author laizonghao
 * @CreateTime 2023/1/10 23:19
 * @Description
 **/
public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerEnum.JSON_SERIALIZER.getAlgCode();
    }

    @Override
    public byte[] serialize(Object src) {
        // fastjson
        return JSON.toJSONBytes(src);
    }

    @Override
    public <T> T deserialize(byte[] srcBytes, Class<T> desClass) {
        return JSON.parseObject(srcBytes,desClass);
    }
}
