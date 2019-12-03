package com.belong.service.gen.config;


import com.belong.service.gen.util.GenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**

* @Description:    GeneratorConfiguration
* @Author:         fengyu
* @CreateDate:     2019/11/27 16:45
* @UpdateUser:     fengyu
* @UpdateDate:     2019/11/27 16:45
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Configuration
@Slf4j
public class GeneratorConfiguration {
    @Bean
    GenUtils genUtils(GeneratorProperties properties) {
        if (log.isDebugEnabled()) {
            log.debug("启用代码生成模块");
        }
        if (properties == null) {
            throw new IllegalArgumentException(String.format("未找到对应sys.generator的配置，请核实！"));
        }
        GenUtils genUtils = new GenUtils(properties);
        return genUtils;
    }


}
