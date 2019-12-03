package com.belong.common.redis.aspect;

import com.belong.common.redis.annotation.RedisCache;
import com.belong.common.redis.annotation.RedisEvict;
import com.belong.common.redis.util.AspectUtil;
import com.belong.common.redis.util.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: redis切面
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:12
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:12
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Component
@Aspect
public class RedisAspect {
    private final static Logger logger = LoggerFactory.getLogger(RedisAspect.class);

    @Autowired
    private RedisUtils redis;

    /**
     * 定义切入点，使用了 @RedisCache 的方法
     */
    @Pointcut("@annotation(com.belong.common.redis.annotation.RedisCache)")
    public void redisCachePoint() {
    }

    @Pointcut("@annotation(com.belong.common.redis.annotation.RedisEvict)")
    public void redisEvictPoint() {
    }

    @After("redisEvictPoint()")
    public void evict(JoinPoint point) {
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        RedisEvict redisEvict = method.getAnnotation(RedisEvict.class);
        // 获取RedisCache注解
        String fieldKey = AspectUtil.parseKey(redisEvict.fieldKey(), method, point.getArgs());
        String rk = redisEvict.key() + ":" + fieldKey;
        logger.debug("<======切面清除rediskey:{} ======>", rk);
        redis.delete(rk);
    }

    /**
     * 环绕通知，方法拦截器
     */
    @Around("redisCachePoint()")
    public Object writeReadFromRedis(ProceedingJoinPoint point) {
        try {
            Method method = ((MethodSignature) point.getSignature()).getMethod();
            // 获取RedisCache注解
            RedisCache redisCache = method.getAnnotation(RedisCache.class);
            Class<?> returnType = ((MethodSignature) point.getSignature()).getReturnType();
            if (redisCache != null && redisCache.read()) {
                // 查询操作
                logger.debug("<======method:{} 进入 redisCache 切面 ======>", method.getName());
                String fieldKey = AspectUtil.parseKey(redisCache.fieldKey(), method, point.getArgs());
                String rk = redisCache.key() + ":" + fieldKey;
                Object obj = redis.get(rk, returnType);
                if (obj == null) {
                    // Redis 中不存在，则从数据库中查找，并保存到 Redis
                    logger.debug("<====== Redis 中不存在该记录，从数据库查找 ======>");
                    obj = point.proceed();
                    if (obj != null) {
                        if (redisCache.expired() > 0) {
                            redis.set(rk, obj, redisCache.expired());
                        } else {
                            redis.set(rk, obj);
                        }
                    }
                }
                return obj;
            }
        } catch (Throwable ex) {
            logger.error("<====== RedisCache 执行异常: {} ======>", ex);
        }
        return null;
    }
}