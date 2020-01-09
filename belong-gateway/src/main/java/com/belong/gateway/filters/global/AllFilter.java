package com.belong.gateway.filters.global;

import com.belong.common.core.constant.Constants;
import com.belong.gateway.config.GatewayContext;
import com.belong.gateway.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;


/**
 * @Description: 全局拦截
 * @Author: fengyu
 * @CreateDate: 2019/9/17 9:33
 * @UpdateDate: 2019/9/17 9:33
 * @Version: 1.0
 */
@Slf4j
@Component
public class AllFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest httpRequest = exchange.getRequest();
        String url = httpRequest.getURI().toString();
        log.info("请求方式=======>{},过滤前的地址=======>{}", httpRequest.getMethod(), url);
        //将path和body缓存到GatewayContext
        GatewayContext gatewayContext = new GatewayContext();
        gatewayContext.setPath(httpRequest.getPath().pathWithinApplication().value());
        // 跳过不需要验证的路径
        exchange.getAttributes().put("startTime", System.currentTimeMillis());
        return returnMono(chain, exchange);
    }

    /**
     * 方法实现说明:计算校验时间
     *
     * @param chain
     * @param exchange
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:47
     */
    private Mono<Void> returnMono(GatewayFilterChain chain, ServerWebExchange exchange) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute("startTime");
            if (startTime != null) {
                long executeTime = (System.currentTimeMillis() - startTime);
                URI url = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
                Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
                LinkedHashSet<URI> uris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
                URI originUri = null;
                if (uris != null) {
                    originUri = uris.stream().findFirst().orElse(null);
                }
                if (url != null && route != null && originUri != null) {
                    log.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                            originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                            route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    );
                }
                log.info("网关转发耗时=======>{}ms,转发状态=======>{}", executeTime, Objects.requireNonNull(exchange.getResponse().getStatusCode()).value());
            }
        }));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
