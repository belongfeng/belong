package com.belong.service.tx.manager.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:40
*/
@Configuration
@EnableAutoConfiguration
public class RestConfig {


	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}


}
