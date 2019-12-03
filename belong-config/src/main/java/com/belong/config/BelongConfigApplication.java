package com.belong.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description: 配置中心服务启动类
 * @Author: fengyu
 * @CreateDate: 2019/10/28 15:32
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/10/28 15:32
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class BelongConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongConfigApplication.class, args);
    }
}