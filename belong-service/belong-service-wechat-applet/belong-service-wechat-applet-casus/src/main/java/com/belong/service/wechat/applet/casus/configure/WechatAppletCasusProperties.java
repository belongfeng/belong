package com.belong.service.wechat.applet.casus.configure;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2020/1/2 15:10
*/
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:application.yml"})
@ConfigurationProperties(prefix = "project")
public class WechatAppletCasusProperties {

    /**
     * 免认证访问路径
     */
    private String anonUrl;
}
