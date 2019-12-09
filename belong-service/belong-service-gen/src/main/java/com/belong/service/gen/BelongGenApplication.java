package com.belong.service.gen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 代码生成启动类
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:48
 * @UpdateDate: 2019/11/27 16:48
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.belong.**.mapper")
public class BelongGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(BelongGenApplication.class, args);
    }
}
