package com.belong.gateway.util;

import com.alibaba.fastjson.JSON;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.Constants;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: ResponseUtil
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:13
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:13
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ResponseUtil {
    /**
     * 方法实现说明:定义返回体
     *
     * @param exchange
     * @param code
     * @param msg
     * @return reactor.core.publisher.Mono<java.lang.Void>
     * @throws
     * @author fengyu
     * @date 2019/9/25 11:51
     */
    public static Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, int code, String msg) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] response = null;
        try {
            response = JSON.toJSONString(ResponseVO.failed(code, msg)).getBytes(Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
        return originalResponse.writeWith(Flux.just(buffer));
    }

    /**
     * 方法实现说明:获取请求body数据
     *
     * @param serverHttpRequest
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/9/25 11:51
     */
    public static String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
