/**
 * Copyright (C), 2019-9999, 喜梁门科技有限公司
 * FileName: websocketSendUtil
 * Author:   FengYu
 * Date:     2019/5/17 9:36
 * Description: 工具类
 * QQ:475788922
 */
package com.belong.common.util.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.net.InetSocketAddress;

/**
 * @Description: WebSocketUtil工具类
 * @Author: fengyu
 * @CreateDate: 2019/11/19 10:42
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/19 10:42
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class WebSocketUtil {
    /**
     * 方法实现说明:得到地址
     *
     * @param channel
     * @return java.net.InetSocketAddress
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:06
     */
    public static InetSocketAddress webSecketGetRemoteAddress(Channel channel) {
        return (InetSocketAddress) channel.remoteAddress();
    }

    /**
     * 方法实现说明:发送消息
     *
     * @param channel
     * @param msg
     * @return boolean
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:06
     */
    public static boolean webSecketSendMsg(Channel channel, String msg) {
        boolean flag = false;
        if (channel != null) {
            if (channel.writeAndFlush(new TextWebSocketFrame(msg)).isDone()) {
                flag = true;
            }

        }
        return flag;
    }

    /**
     * 方法实现说明:关闭当前chanel
     *
     * @param channel
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:06
     */
    public static void close(Channel channel) {
        if (channel != null) {
            channel.close();
        }
    }

}
