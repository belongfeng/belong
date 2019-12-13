package com.belong.service.tx.manager.netty.service;

import com.alibaba.fastjson.JSONObject;

/**
* @Description:    
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
public interface IActionService {


	String execute(String channelAddress, String key, JSONObject params);

}
