package com.belong.service.tx.manager.listener;

import com.belong.service.tx.manager.listener.service.InitService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:41
*/

@Component
public class ServerListener implements ServletContextListener {

	private WebApplicationContext springContext;


	private InitService initService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		springContext = WebApplicationContextUtils
			.getWebApplicationContext(event.getServletContext());
		initService = springContext.getBean(InitService.class);
		initService.start();
	}


	@Override
	public void contextDestroyed(ServletContextEvent event) {
		initService.close();
	}

}
