package cn.zephyr.ch16.common;

import io.netty.util.AttributeKey;

/**
 * @ClassName MyAttributes
 * @Author laizonghao
 * @CreateTime 2023/2/3 19:14
 * @Description
 **/
public class MyAttributes {
    public static final AttributeKey<MySession> MY_SESSION = AttributeKey.newInstance("mySession");
}
