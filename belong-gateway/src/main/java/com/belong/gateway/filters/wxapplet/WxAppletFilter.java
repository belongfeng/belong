///**
// * Copyright (C), 2019-9999, 喜梁门科技有限公司
// * FileName: SystemFilter
// * Author:   FengYu
// * Date:     2019/7/18 14:10
// * Description: 后台拦截鉴权
// * QQ:475788922
// */
//package com.belong.gateway.filters.wxapplet;
//
//import com.server.common.constant.Constants;
//import com.server.common.utils.StringUtils;
//import com.server.common.utils.jwt.WxappletJwtUtil;
//import com.server.gateway.config.GatewayContext;
//import com.server.gateway.util.ResponseUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
//
//@Component
//@Slf4j
//public class WxAppletFilter extends AbstractGatewayFilterFactory<WxAppletFilter.Config> {
//    @Resource
//    private WxappletJwtUtil wxappletJwtUtil;
//    /**
//     * 放过swagger
//     */
//    private static final String[] SWAGGER_WHITE_LIST = {"/v2/api-docs", "/v2/api-docs-ext"};
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            try {
//                ServerHttpRequest request = exchange.getRequest();
//                String url = request.getURI().getPath();
//                log.debug("wxapplet拦截的路径为{}" + url);
//                // 跳过不需要验证的路径
//                if (Arrays.asList(SWAGGER_WHITE_LIST).contains(url)) {
//                    return chain.filter(exchange);
//                }
//                String token = exchange.getRequest().getHeaders().getFirst(Constants.TOKEN);
//                if (StringUtils.isBlank(token)) {
//                    return ResponseUtil.setUnauthorizedResponse(exchange, 401,"不知道携带token？");
//                }
//                //校验token并自动续费
//                if (!wxappletJwtUtil.verify(token)) {
//                    return ResponseUtil.setUnauthorizedResponse(exchange, 401,"token过期了！");
//                }
//                //检查body是否包含请求数据
//                MediaType mediaType = request.getHeaders().getContentType();
//                if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType) || MediaType.APPLICATION_JSON_UTF8.isCompatibleWith(mediaType)) {
//                    GatewayContext gatewayContext = exchange.getAttribute(GatewayContext.CACHE_GATEWAY_CONTEXT);
//                    String bodyStr = gatewayContext.getCacheBody();
//                    if (StringUtils.isEmpty(bodyStr)) {
//                        return ResponseUtil.setUnauthorizedResponse(exchange, 401,"body没有参数你用什么post啊？");
//                    }
//                }
//                return chain.filter(exchange);
//            } catch (Exception e) {
//                log.info("请求发生错误！");
//                return ResponseUtil.setUnauthorizedResponse(exchange, -1,"系统错误，请检查请求是否正确！");
//            }
//        };
//    }
//
//    public WxAppletFilter() {
//        super(Config.class);
//    }
//
//    public static class Config {
//    }
//}
