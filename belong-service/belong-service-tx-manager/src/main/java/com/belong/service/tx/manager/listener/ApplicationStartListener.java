package com.belong.service.tx.manager.listener;

import com.belong.service.tx.manager.utils.Constants;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:41
*/
@Component
public class ApplicationStartListener implements ApplicationListener<WebServerInitializedEvent> {


	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		int serverPort = event.getWebServer().getPort();
		String ip = getIp();
		Constants.address = ip + ":" + serverPort;
	}


	private String getIp() {
		String host = null;
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return host;
	}
}
