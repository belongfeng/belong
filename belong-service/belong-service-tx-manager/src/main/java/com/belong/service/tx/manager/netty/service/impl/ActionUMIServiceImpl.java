package com.belong.service.tx.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.belong.service.tx.manager.manager.ModelInfoManager;
import com.belong.service.tx.manager.model.ModelInfo;
import com.belong.service.tx.manager.netty.service.IActionService;
import com.belong.service.tx.manager.utils.SocketManager;
import org.springframework.stereotype.Service;

/**
* @Description:    上传模块信息
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
@Service(value = "umi")
public class ActionUMIServiceImpl implements IActionService {


	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res = "1";

		String uniqueKey = params.getString("u");
		String ipAddress = params.getString("i");
		String model = params.getString("m");


		ModelInfo modelInfo = new ModelInfo();
		modelInfo.setChannelName(channelAddress);
		modelInfo.setIpAddress(ipAddress);
		modelInfo.setModel(model);
		modelInfo.setUniqueKey(uniqueKey);

		ModelInfoManager.getInstance().addModelInfo(modelInfo);

		SocketManager.getInstance().onLine(channelAddress, uniqueKey);

		return res;
	}
}
