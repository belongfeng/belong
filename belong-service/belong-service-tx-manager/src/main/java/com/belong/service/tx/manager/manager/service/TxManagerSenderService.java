package com.belong.service.tx.manager.manager.service;


import com.belong.service.tx.manager.netty.model.TxGroup;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:42
*/
public interface TxManagerSenderService {

	int confirm(TxGroup group);

	String sendMsg(String model, String msg, int delay);

	String sendCompensateMsg(String model, String groupId, String data, int startState);
}
