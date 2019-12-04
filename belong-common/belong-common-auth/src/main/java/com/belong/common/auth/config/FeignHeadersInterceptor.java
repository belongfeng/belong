package com.belong.common.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @Description: 加上该注解 ，则不需要FeignClient里面加属性configuration
 * @Author: fengyu
 * @CreateDate: 2019/12/4 16:07
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/12/4 16:07
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
public class FeignHeadersInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        HttpServletRequest request = getHttpServletRequest();

        if (Objects.isNull(request)) {
            return;
        }

        Map<String, String> headers = getHeaders(request);
        if (headers.size() > 0) {
            Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                // 把请求过来的header请求头 原样设置到feign请求头中
                // 包括token
                template.header(entry.getKey(), entry.getValue());
            }
        }
    }

    private HttpServletRequest getHttpServletRequest() {

        try {
            // 这种方式获取的HttpServletRequest是线程安全的
            return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        } catch (Exception e) {

            return null;
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {

        Map<String, String> map = new LinkedHashMap<>();

        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}