package com.belong.common.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.belong.common.core.base.ResponseVO;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname BelongAccessDeniedHandler
 * @Description TODO
 * @Date 2020/1/2 15:17
 * @Created by FengYu
 */
public class BelongAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getOutputStream().write(JSONObject.toJSONString(ResponseVO.failed(401, "没有权限访问该资源!")).getBytes());
    }
}
