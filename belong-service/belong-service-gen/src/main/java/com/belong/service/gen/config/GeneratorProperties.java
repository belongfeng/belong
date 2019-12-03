package com.belong.service.gen.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**

* @Description:    GeneratorProperties
* @Author:         fengyu
* @CreateDate:     2019/11/27 16:46
* @UpdateUser:     fengyu
* @UpdateDate:     2019/11/27 16:46
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@Component
@ConfigurationProperties(prefix = "sys")
@Data
public class GeneratorProperties {
    private Map<String, String> generator;
}
