package com.belong.service.tx.manager.netty.service.impl;

import com.belong.service.tx.manager.netty.service.IActionService;
import com.belong.service.tx.manager.netty.service.NettyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
* @Description:    
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:45
*/
@Service
public class NettyServiceImpl implements NettyService {

	@Autowired
	private ApplicationContext spring;

	@Override
	public IActionService getActionService(String action) {
		return spring.getBean(action, IActionService.class);
	}
}
