package com.belong.gateway.handler;

import com.belong.common.core.base.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.belong.common.core.constant.ResponseCodeConstans.SERVICE_NOT_FOUND;
import static com.belong.common.core.constant.ResponseCodeConstans.UN_KNOWX_ERROR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

/**
 * @Description: 服务降级处理
 * @Author: fengyu
 * @CreateDate: 2019/11/27 10:57
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 10:57
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse> {
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error("网关执行请求:{}失败,hystrix服务降级处理！", originalUri));
        return ServerResponse.status(UN_KNOWX_ERROR).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(ResponseVO.failed(SERVICE_NOT_FOUND, "服务未启动完成,稍等一会,请尝试重新请求！")));
    }
}