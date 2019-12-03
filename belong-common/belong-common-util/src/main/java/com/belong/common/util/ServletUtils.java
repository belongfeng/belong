package com.belong.common.util;

import com.belong.common.util.str.Convert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: 客户端工具类
 * @Author: fengyu
 * @CreateDate: 2019/11/28 14:48
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/28 14:48
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ServletUtils {
    /**
     * 方法实现说明:获取String参数
     *
     * @param name
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 方法实现说明:获取String参数
     *
     * @param name
     * @param defaultValue
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 方法实现说明:获取Integer参数
     *
     * @param name
     * @return java.lang.Integer
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 方法实现说明:获取Integer参数
     *
     * @param name
     * @param defaultValue
     * @return java.lang.Integer
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 方法实现说明:获取Integer参数
     *
     * @param name
     * @return java.lang.Integer
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static Long getParameterToLong(String name) {
        return Convert.toLong(getRequest().getParameter(name));
    }

    /**
     * 方法实现说明:获取request
     *
     * @param
     * @return javax.servlet.http.HttpServletRequest
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 方法实现说明:获取response
     *
     * @param
     * @return javax.servlet.http.HttpServletResponse
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:49
     */
    public static HttpServletResponse getResponse() {

        return getRequestAttributes().getResponse();
    }

    /**
     * 方法实现说明:获取session
     *
     * @param
     * @return javax.servlet.http.HttpSession
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:50
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 方法实现说明:将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:50
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法实现说明:是否是Ajax异步请求
     *
     * @param request
     * @return boolean
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:50
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")) {
            return true;
        }
        return false;
    }
}
