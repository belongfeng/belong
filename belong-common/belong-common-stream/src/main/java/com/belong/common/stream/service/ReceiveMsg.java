package com.belong.common.stream.service;

import org.springframework.messaging.Message;

/**
 * @Description: 接收消息接口
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:56
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:56
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface ReceiveMsg {
    public void receive(Message<String> message);
}
