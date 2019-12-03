package com.belong.common.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.belong.common.datasource.datasource.DynamicDataSource;
import com.belong.common.datasource.properties.DruidProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description: druid 配置多数据源
 * @Author: fengyu
 * @CreateDate: 2019/11/20 14:31
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/20 14:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class DruidConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        if (Optional.ofNullable(dataSource).isPresent()) {
            log.info("正在加载主数据源{}", DataSourceType.MASTER.name());
            dataSource = druidProperties.dataSource(dataSource);
        }
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        if (Optional.ofNullable(dataSource).isPresent()) {
            log.info("正在加载从数据源{}", DataSourceType.SLAVE.name());
            dataSource = druidProperties.dataSource(dataSource);
        }
        dataSource = druidProperties.dataSource(dataSource);
        return dataSource;
    }



    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);

        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
