package com.belong.common.datasource.aspectj;

import com.belong.common.datasource.annotation.DataSource;
import com.belong.common.datasource.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @Description: 多数据源切换处理
 * @Author: fengyu
 * @CreateDate: 2019/10/22 15:56
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/10/22 15:56
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class DataSourceAspect {

    @Pointcut("@annotation(com.belong.common.datasource.annotation.DataSource)"
            + "|| @within(com.belong.common.datasource.annotation.DataSource)")
    public void dsPointCut() {
        log.info("进入数据源切换切面");
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);
        if (Optional.ofNullable(dataSource).isPresent()) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 方法实现说明:获取需要切换的数据源
     *
     * @param point
     * @return com.belong.common.datasource.annotation.DataSource
     * @throws
     * @author fengyu
     * @date 2019/11/27 9:56
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (Optional.ofNullable(targetDataSource).isPresent()) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);
            return dataSource;
        }
    }
}
