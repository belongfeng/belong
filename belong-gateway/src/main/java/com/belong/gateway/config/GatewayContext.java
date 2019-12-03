package com.belong.gateway.config;

import lombok.Data;

/**
 * @Description: 存储请求缓存
 * @Author: fengyu
 * @CreateDate: 2019/11/27 10:20
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 10:20
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
public class GatewayContext {

    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";

    /**
     * cache json body
     */
    private String cacheBody;

    /**
     * cache reqeust path
     */
    private String path;

}
