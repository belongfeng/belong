package com.belong.gateway.filters.wxapplet;

import com.belong.common.core.constant.Constants;
import com.belong.gateway.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description: 微信小程序拦截器
 * @Author: fengyu
 * @CreateDate: 2019/12/5 10:07
 * @UpdateDate: 2019/12/5 10:07
 * @Version: 1.0
 */
@Component
@Slf4j
public class WxAppletFilter extends AbstractGatewayFilterFactory<WxAppletFilter.Config> {
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 白名单
     */
    private static final String[] WHITE_LIST = {"/v2/api-docs", "/v2/api-docs-ext", "/actuator"
            , "/test", "/v1/api/wxUserAuth/baseLogin"
            , "/v1/api/wxUserAuth/completeLogin"
            , "/v1/api/wxUserAuth/userInfo"
    };

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest httpRequest = exchange.getRequest();
            String url = httpRequest.getURI().toString();
            log.info("请求方式=======>{},过滤后的地址=======>{}", httpRequest.getMethod(), url);
            String path = httpRequest.getURI().getPath();
            // 跳过不需要验证的路径
            if (Arrays.asList(WHITE_LIST).contains(path)) {
                return chain.filter(exchange);
            }
            String token = exchange.getRequest().getHeaders().getFirst(Constants.AUTHORIZATION);
            //判断是否存在Token
            if (StringUtils.isBlank(token) || !token.startsWith(Constants.TOKEN_TYPE_BEARER)) {
                return ResponseUtil.setUnauthorizedResponse(exchange, 401, "请携带关键字=======>" + Constants.AUTHORIZATION);
            }
            return chain.filter(exchange);
        };
    }

    public WxAppletFilter() {
        super(Config.class);
    }

    public static class Config {
    }
}
