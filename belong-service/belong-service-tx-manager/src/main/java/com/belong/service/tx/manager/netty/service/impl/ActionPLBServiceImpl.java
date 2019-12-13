package com.belong.service.tx.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.belong.service.tx.manager.manager.service.LoadBalanceService;
import com.belong.service.tx.manager.model.LoadBalanceInfo;
import com.belong.service.tx.manager.netty.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description:    添加负载模块信息
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
@Service(value = "plb")
public class ActionPLBServiceImpl implements IActionService {


	@Autowired
	private LoadBalanceService loadBalanceService;


	@Override
	public String execute(String channelAddress, String key, JSONObject params) {

		String groupId = params.getString("g");
		String k = params.getString("k");
		String data = params.getString("d");

		LoadBalanceInfo loadBalanceInfo = new LoadBalanceInfo();
		loadBalanceInfo.setData(data);
		loadBalanceInfo.setKey(k);
		loadBalanceInfo.setGroupId(groupId);
		boolean ok = loadBalanceService.put(loadBalanceInfo);

		return ok ? "1" : "0";
	}
}
