package com.belong.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: 网关服务启动类
 * @Author: fengyu
 * @CreateDate: 2019/10/28 15:31
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/10/28 15:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@EnableEurekaServer
@EnableEurekaClient
@SpringBootApplication
public class BelongEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongEurekaApplication.class, args);
    }
}