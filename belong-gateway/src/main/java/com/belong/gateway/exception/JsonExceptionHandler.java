package com.belong.gateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 将异常处理为json
 * @Author: fengyu
 * @CreateDate: 2019/11/27 10:27
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 10:27
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {


    public JsonExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                                ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }


    /**
     * 方法实现说明:获取异常属性
     *
     * @param request
     * @param includeStackTrace
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:28
     */
    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        Throwable error = super.getError(request);
        if (error instanceof org.springframework.cloud.gateway.support.NotFoundException) {
            code = HttpStatus.NOT_FOUND.value();
        }
        return response(code, this.buildMessage(request, error));
    }

    /**
     * 方法实现说明:指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes
     * @return org.springframework.web.reactive.function.server.RouterFunction<org.springframework.web.reactive.function.server.ServerResponse>
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:27
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }


    /**
     * 方法实现说明:根据code获取对应的HttpStatus
     *
     * @param errorAttributes
     * @return org.springframework.http.HttpStatus
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:28
     */
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int) errorAttributes.get("code");
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 方法实现说明:构建异常信息
     *
     * @param request
     * @param ex
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:28
     */
    private String buildMessage(ServerRequest request, Throwable ex) {
        StringBuilder message = new StringBuilder("服务请求失败,[");
        message.append(request.methodName());
        message.append(" ");
        message.append(request.uri());
        message.append("]");
        if (ex != null) {
            message.append(": ");
            message.append(ex.getMessage());
        }
        return message.toString();
    }

    /**
     * 方法实现说明:构建返回的JSON数据格式
     *
     * @param status
     * @param errorMessage
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @author fengyu
     * @date 2019/11/27 10:28
     */
    public static Map<String, Object> response(int status, String errorMessage) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", status);
        map.put("msg", errorMessage);
        map.put("data", null);
        log.error(map.toString());
        return map;
    }
}