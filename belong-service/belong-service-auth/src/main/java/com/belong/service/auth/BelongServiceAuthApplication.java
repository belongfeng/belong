package com.belong.service.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 微信小程序个人中心服务以及扫码服务启动类
 * @Author: fengyu
 * @CreateDate: 2019/10/28 15:27
 * @UpdateDate: 2019/10/28 15:27
 * @Version: 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.belong"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.belong"})
@MapperScan("com.belong.service.auth.mapper")
@EnableTransactionManagement
public class BelongServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongServiceAuthApplication.class, args);
    }
}