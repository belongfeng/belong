package com.belong.common.redis.annotation;

import java.lang.annotation.*;

/**
 * @Description: redis删除注解
 * @Author: fengyu
 * @CreateDate: 2019/11/20 14:23
 * @UpdateDate: 2019/11/20 14:23
 * @Version: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisEvict {
    String key();

    String fieldKey();
}