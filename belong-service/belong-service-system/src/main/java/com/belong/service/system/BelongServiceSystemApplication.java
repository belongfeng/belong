package com.belong.service.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description: 系统后台启动
 * @Author: fengyu
 * @CreateDate: 2019/10/28 15:27
 * @UpdateDate: 2019/10/28 15:27
 * @Version: 1.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.belong"})
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.belong"})
@MapperScan("com.belong.service.system.mapper")
@EnableTransactionManagement
public class BelongServiceSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongServiceSystemApplication.class, args);
    }
}