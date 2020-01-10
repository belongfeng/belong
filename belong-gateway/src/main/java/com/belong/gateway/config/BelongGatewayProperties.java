package com.belong.gateway.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2020/1/9 10:37
*/
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:application.yml"})
@ConfigurationProperties(prefix = "belong.gateway")
public class BelongGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;

    private String AnonRequestUrl;

    private String WxAppletAnonRequestUrl;


    private String AuthAnonRequestUrl;
}
