package com.belong.common.auth.security;

import com.alibaba.fastjson.JSONObject;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.ResponseCodeConstans;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: The type My authentication entry point.
 * @Author: fengyu
 * @CreateDate: 2019/12/3 15:04
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/12/3 15:04
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未授权");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSONObject.toJSONString(ResponseVO.failed(ResponseCodeConstans.UN_AUTHORIZED, "无权限或登陆失效!")));
    }
}