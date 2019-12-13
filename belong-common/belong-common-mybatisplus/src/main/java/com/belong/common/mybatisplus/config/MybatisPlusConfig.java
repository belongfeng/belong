package com.belong.common.mybatisplus.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: mybatisplus配置类
 * @Author: fengyu
 * @CreateDate: 2019/11/27 10:02
 * @UpdateDate: 2019/11/27 10:02
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class MybatisPlusConfig {

    /**
     * 方法实现说明:乐观锁插件
     *
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:02
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 方法实现说明:分页插件
     *
     * @param
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:02
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
}
