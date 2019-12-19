package com.belong.common.auth.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.*;

/**
 * @Classname 限制同一个接口不停被请求
 * @Description TODO
 * @Date 2019/12/6 17:24
 * @Created by FengYu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AccessLimit {
    //请求的时间范围
    int seconds() default 5;
    //时间范围里面具体可以访问的次数
    int maxCount() default 8;

    boolean isLogin() default true;
}

