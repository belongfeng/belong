package com.belong.service.tx.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* @Description:    tx-manager ，进行了代码逻辑和代码规范重构
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:46
*/
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class BelongTxManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(BelongTxManagerApplication.class, args);
	}

}
