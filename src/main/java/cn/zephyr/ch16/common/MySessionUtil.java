package cn.zephyr.ch16.common;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MySessionUtil
 * @Author laizonghao
 * @CreateTime 2023/2/3 19:05
 * @Description
 **/
public class MySessionUtil {

    public static final Map<String, Channel> userIdAndChannelContainer = new ConcurrentHashMap<>();

    /**
     * 绑定 session
     * @param session
     * @param channel
     */
    public static void bindSession(MySession session, Channel channel) {
        userIdAndChannelContainer.put(session.getUserId(),channel);
        channel.attr(MyAttributes.MY_SESSION).set(session);
    }

    /**
     * 解绑 session
     * @param channel
     */
    public static void unbindSession(Channel channel) {
        userIdAndChannelContainer.remove(getSession(channel).getUserId());
        channel.attr(MyAttributes.MY_SESSION).set(null);
    }

    /**
     * 当前客户端是否已登录
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(MyAttributes.MY_SESSION);
    }

    /**
     * 获取当前客户端的 session 信息
     * @param channel
     * @return
     */
    public static MySession getSession(Channel channel) {
        return channel.attr(MyAttributes.MY_SESSION).get();
    }

    /**
     *
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId) {
        return userIdAndChannelContainer.get(userId);
    }




}
