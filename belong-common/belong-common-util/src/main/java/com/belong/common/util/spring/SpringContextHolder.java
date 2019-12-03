package com.belong.common.util.spring;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Description: SpringContextHolder
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:14
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:14
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext = null;

    /**
     * 方法实现说明:取得存储在静态变量中的ApplicationContext
     *
     * @param
     * @return org.springframework.context.ApplicationContext
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:26
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 方法实现说明:实现ApplicationContextAware接口, 注入Context到静态变量中.
     *
     * @param applicationContext
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:26
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 方法实现说明:清除SpringContextHolder中的ApplicationContext为Null
     *
     * @param
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:26
     */
    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 方法实现说明:从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param beanName
     * @return T
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:26
     */
    public static <T> T getBean(String beanName) throws BeansException {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 方法实现说明:从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     *
     * @param clazz
     * @return T
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:25
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 方法实现说明:发布事件
     *
     * @param event
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:25
     */
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext == null) {
            return;
        }
        applicationContext.publishEvent(event);
    }

    /**
     * 方法实现说明:实现DisposableBean接口, 在Context关闭时清理静态变量.
     *
     * @param
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:26
     */
    @Override
    @SneakyThrows
    public void destroy() {
        SpringContextHolder.clearHolder();
    }
}