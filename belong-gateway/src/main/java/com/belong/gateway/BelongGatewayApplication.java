package com.belong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 网关服务启动类
 * @Author: fengyu
 * @CreateDate: 2019/10/28 15:31
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/10/28 15:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = {"com.belong"})
public class BelongGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongGatewayApplication.class, args);
    }

}