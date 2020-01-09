package com.belong.gateway.filters.wxapplet;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.Constants;
import com.belong.gateway.config.BelongGatewayProperties;
import com.belong.gateway.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

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
    @Autowired
    private BelongGatewayProperties properties;
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            //限制访问为最优先，先检查是否被限制访问
            checkForbidUri(request, response);
            //再次检查是否需要token
            if (checkAnonUri(request)) {
                return chain.filter(exchange);
            }
            String token = exchange.getRequest().getHeaders().getFirst(Constants.AUTHORIZATION);
            //判断是否存在Token
            if (StringUtils.isBlank(token) || !token.startsWith(Constants.TOKEN_TYPE_BEARER)) {
                return ResponseUtil.setUnauthorizedResponse(response, 401, "请携带关键字=======>" + Constants.AUTHORIZATION);
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 方法实现说明: 判断是否是无需验证的路径
     *
     * @param request
     * @return boolean
     * @throws
     * @author belongfeng
     */
    private boolean checkAnonUri(ServerHttpRequest request) {
        String uri = request.getPath().toString();
        boolean shouldForward = false;
        String anonRequestUrl = properties.getAnonRequestUrl();
        String[] anonRequestUris = anonRequestUrl.split(",");
        if (anonRequestUris != null && ArrayUtils.isNotEmpty(anonRequestUris)) {
            for (String u : anonRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = true;
                }
            }
        }
        return shouldForward;
    }


    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = false;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = true;
                }
            }
        }
        if (shouldForward) {
            return ResponseUtil.setUnauthorizedResponse(response, 401, "该URI不允许外部访问!");
        }
        return null;
    }

    public WxAppletFilter() {
        super(Config.class);
    }

    public static class Config {
    }
}
