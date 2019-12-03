package com.belong.gateway.config;

import com.belong.common.util.IpUtils;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;


/**
 * @Description: 路由限流配置
 * @Author: fengyu
 * @CreateDate: 2019/9/19 13:53
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/9/19 13:53
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class RateLimiterConfiguration {
    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono.just(IpUtils.getIpAddrByServerHttpRequest(exchange.getRequest()));
    }
}