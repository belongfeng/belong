package com.belong.service.tx.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.belong.service.tx.manager.config.ConfigReader;
import com.belong.service.tx.manager.netty.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description:    心跳包
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
@Service(value = "h")
public class ActionHServiceImpl implements IActionService {


	@Autowired
	private ConfigReader configReader;

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		return String.valueOf(configReader.getTransactionNettyDelayTime());
	}
}
