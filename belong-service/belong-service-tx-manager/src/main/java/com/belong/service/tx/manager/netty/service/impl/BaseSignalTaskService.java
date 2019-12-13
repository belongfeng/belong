package com.belong.service.tx.manager.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lorne.core.framework.utils.task.ConditionUtils;
import com.lorne.core.framework.utils.task.Task;

/**
* @Description:    
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
public class BaseSignalTaskService {

	public String execute(String channelAddress, String key, JSONObject params) {
		String res = "";
		final String data = params.getString("d");
		Task task = ConditionUtils.getInstance().getTask(key);
		if (task != null) {
			task.setBack(objs -> data);
			task.signalTask();
		}
		return res;
	}
}
