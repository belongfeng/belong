package com.belong.common.stream.impl;

import com.belong.common.stream.service.ReceiveMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:55
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
@EnableBinding(value = {Sink.class})
@Slf4j
public class ReceiveMsgImpl implements ReceiveMsg {

    @Override
    @StreamListener(Sink.INPUT)
    public void receive(Message<String> message) {
        log.info("接收消息" + message.getPayload());
    }

}