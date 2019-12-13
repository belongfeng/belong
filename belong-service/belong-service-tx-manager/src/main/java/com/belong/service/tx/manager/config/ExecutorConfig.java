package com.belong.service.tx.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
* @Description:    LCN线程池配置
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:40
*/
@Configuration
public class ExecutorConfig {
	private static final String LCN_SERVICE = "lcn-service-";

	@Bean
	public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		//配置核心线程数
		executor.setCorePoolSize(10);
		//配置最大线程数
		executor.setMaxPoolSize(50);
		//配置队列大小
		executor.setQueueCapacity(99999);
		//配置线程池中的线程的名称前缀
		executor.setThreadNamePrefix(LCN_SERVICE);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//执行初始化
		executor.initialize();
		return executor;
	}
}
