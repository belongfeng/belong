package com.belong.service.tx.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.belong.service.tx.manager.manager.service.LoadBalanceService;
import com.belong.service.tx.manager.model.LoadBalanceInfo;
import com.belong.service.tx.manager.netty.service.IActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description:    获取负载模块信息
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
@Service(value = "glb")
public class ActionGLBServiceImpl implements IActionService {


	@Autowired
	private LoadBalanceService loadBalanceService;


	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res;
		String groupId = params.getString("g");
		String k = params.getString("k");

		LoadBalanceInfo loadBalanceInfo = loadBalanceService.get(groupId, k);
		if (loadBalanceInfo == null) {
			res = "";
		} else {
			res = loadBalanceInfo.getData();
		}
		return res;
	}
}
