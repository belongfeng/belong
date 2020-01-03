package com.belong.service.auth.config.properties;

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
@PropertySource(value = {"classpath:bootstrap.yml"})
@ConfigurationProperties(prefix = "belong.auth")
public class AuthProperties {

    /**
     * 免认证访问路径
     */
    private String anonUrl;
    /**
     * JWT加签密钥
     */
    private String jwtAccessKey;
    /**
     * 是否使用 JWT令牌
     */
    private Boolean enableJwt;
    /**
     * Token 刷新时间
     */
    private int refreshTokenSeconds;
    /**
     * Token 失效时间
     */
    private int accessTokenSeconds;
}
