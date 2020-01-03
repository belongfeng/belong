package com.belong.common.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.belong.common.core.base.ResponseVO;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname BelongAuthExceptionEntryPoint
 * @Description TODO
 * @Date 2020/1/2 15:20
 * @Created by FengYu
 */
public class BelongAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.getOutputStream().write(JSONObject.toJSONString(ResponseVO.failed(401, "token无效!")).getBytes());
    }
}
