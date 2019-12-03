package com.belong.common.redis.annotation;

import java.lang.annotation.*;

/**
 * @Description: redis缓存注解
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:12
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:12
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    /**
     * 键名
     *
     * @return
     */
    String key() default "";

    /**
     * 主键
     *
     * @return
     * @author fengyu
     */
    String fieldKey();

    /**
     * 过期时间
     *
     * @return
     */
    long expired() default 3600;

    /**
     * 是否为查询操作
     * 如果为写入数据库的操作，该值需置为 false
     *
     * @return
     */
    boolean read() default true;
}