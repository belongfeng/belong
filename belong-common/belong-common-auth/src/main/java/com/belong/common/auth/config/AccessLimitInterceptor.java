package com.belong.common.auth.config;

import com.alibaba.fastjson.JSON;
import com.belong.common.auth.annotation.AccessLimit;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.constant.Constants;
import com.belong.common.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;


/**
 * @Classname AccessLimitInterceptor
 * @Description 接口防止恶意请求
 * @Date 2019/12/6 17:31
 * @Created by FengYu
 */
@Component
@Slf4j
public class AccessLimitInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //判断请求是否属于方法的请求
            if (handler instanceof HandlerMethod) {
                HandlerMethod hm = (HandlerMethod) handler;
                //获取方法中的注解,看是否有该注解
                AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
                //没有注解直接放过
                if (accessLimit == null) {
                    return true;
                }
                int seconds = accessLimit.seconds();
                int maxCount = accessLimit.maxCount();
                boolean isLogin = accessLimit.isLogin();
                String key = request.getRequestURI();
                //根据是否需要登录进行token或者ip限流
                if (isLogin) {
                    //将根据token进行限流
                    String token = request.getHeader(Constants.AUTHORIZATION);
                    if (token == null || "".equals(token)) {
                        render(response, "请携带token！");
                        return false;
                    }
                    //存hash值是因为token太长影响 索引速度，弟弟莫乱改
                    key = key + ":" + DigestUtils.sha1DigestAsHex(token);
                } else {
                    String ip = IpUtils.getIpAddr(request);
                    key = key + ":" + ip;
                }
                //根据key获取已请求次数
                Long count = redisTemplate.opsForValue().increment(key, 1);
                log.info("访问次数为：{}", count == null || "".equals(count.longValue()) ? 1 : count);
                if (count == 1) {
                    redisTemplate.opsForValue().set(key, "1", seconds, TimeUnit.SECONDS);
                }
                if (count > maxCount) {
                    render(response, "请不要频繁请求！多次频繁将会进入黑名单！");
                    return false;
                }
            }
        } catch (Exception e) {
            log.info("错误信息：{}", e.getMessage());
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
