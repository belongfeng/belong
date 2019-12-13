package com.belong.service.tx.manager.listener.service.impl;

import com.belong.service.tx.manager.config.ConfigReader;
import com.belong.service.tx.manager.listener.service.InitService;
import com.belong.service.tx.manager.netty.service.NettyServerService;
import com.belong.service.tx.manager.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:41
*/
@Service
public class InitServiceImpl implements InitService {

	@Autowired
	private NettyServerService nettyServerService;

	@Autowired
	private ConfigReader configReader;


	@Override
	public void start() {
		/**加载本地服务信息**/

		Constants.socketPort = configReader.getSocketPort();
		Constants.maxConnection = configReader.getSocketMaxConnection();
		nettyServerService.start();
	}

	@Override
	public void close() {
		nettyServerService.close();
	}
}
