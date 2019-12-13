package com.belong.service.tx.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.belong.service.tx.manager.manager.service.TxManagerService;
import com.belong.service.tx.manager.netty.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description:    关闭事务组
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
@Service(value = "ctg")
public class ActionCTGServiceImpl implements IActionService {


	@Autowired
	private TxManagerService txManagerService;

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String groupId = params.getString("g");
		int state = params.getInteger("s");
		String res = String.valueOf(txManagerService.closeTransactionGroup(groupId, state));
		return res;
	}
}
