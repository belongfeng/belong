package com.belong.common.stream.impl;

import com.belong.common.stream.service.SendMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
/**

* @Description:
* @Author:         fengyu
* @CreateDate:     2019/11/27 15:56
* @UpdateUser:     fengyu
* @UpdateDate:     2019/11/27 15:56
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Slf4j
@EnableBinding(value = {Source.class})
public class SendMsgImpl implements SendMsg {

	@Resource
	private MessageChannel output; // 消息的发送管道

	@Override
	public void send(String msg) {
		log.info(msg);
		this.output.send(MessageBuilder.withPayload(msg).build());
	}

}