package com.belong.common.dozer.config;

import com.belong.common.dozer.service.IGenerator;
import com.belong.common.dozer.service.MyGenerator;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * @Description: Dozer转换
 * @Author: fengyu
 * @CreateDate: 2019/11/27 14:35
 * @UpdateDate: 2019/11/27 14:35
 * @Version: 1.0
 */
@Configuration
public class DozerMapperConfig {
    @Bean
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozer/*.xml") Resource[] resources) throws Exception {
        final DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }
}
