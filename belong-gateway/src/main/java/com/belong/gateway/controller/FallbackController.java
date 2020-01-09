package com.belong.gateway.controller;

import com.belong.common.core.base.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2020/1/9 17:36
*/
@RestController
public class FallbackController {

    @RequestMapping("fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseVO> systemFallback(@PathVariable String name) {
        String response = String.format("访问%s超时或者服务不可用", name);
        return Mono.just(ResponseVO.failed(response));
    }

}
