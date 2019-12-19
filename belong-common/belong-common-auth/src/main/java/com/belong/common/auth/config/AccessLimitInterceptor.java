package com.belong.common.auth.config;

import com.alibaba.fastjson.JSON;
import com.belong.common.auth.annotation.AccessLimit;
import com.belong.common.auth.util.WebUtils;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Classname AccessLimitInterceptor
 * @Description 接口防刷
 * @Date 2019/12/6 17:31
 * @Created by FengYu
 */
@Component
@Slf4j
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //判断请求是否属于方法的请求
            if (handler instanceof HandlerMethod) {
                redisTemplate.opsForValue().set("fengyu", 1);
                HandlerMethod hm = (HandlerMethod) handler;
                //获取方法中的注解,看是否有该注解
                AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
                //没有注解直接放过
                if (accessLimit == null) {
                    log.info("没有限流注解直接放过！");
                    return true;
                }
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                boolean isLogin = accessLimit.isLogin();
                String key = request.getRequestURI();
                //根据是否需要登录进行token或者ip限流
                if (isLogin) {
                    //将根据token进行限流
                    String openId = WebUtils.getCurrentUser().getUsername();
                    key = key + ":" + openId;
                } else {
                    String ip = IpUtils.getIpAddr(request);
                    key = key + ":" + ip;
                }
                //根据key获取已请求次数
                Integer count = (Integer) redisTemplate.opsForValue().get(key);
                if (count == null) {
                    //第一次访问
                    redisTemplate.opsForValue().set(key, 1, seconds, TimeUnit.SECONDS);
                } else if (count < maxCount) {
                    //加1
                    redisTemplate.opsForValue().increment(key, count + 1);
                } else {
                    //超出访问次数
                    render(response, "请不要频繁请求！再次频繁请求将封号！");
                    return false;
                }
            }
        } catch (Exception e) {
            log.info("错误信息：{}",e.getMessage());
            //超出访问次数
            render(response, "请查看redis配置！");
            return false;
        }
        return true;
    }

    private void render(HttpServletResponse response, String msg) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(ResponseVO.failed(msg));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
