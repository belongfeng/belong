package com.belong.common.datasource.annotation;

import com.belong.common.datasource.config.DataSourceType;

import java.lang.annotation.*;

/**
 * @Description: 自定义多数据源切换注解
 * @Author: fengyu
 * @CreateDate: 2019/11/20 14:31
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/20 14:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {

    /**
     * 方法实现说明:切换数据源名称
     *
     * @param null
     * @return
     * @throws
     * @author fengyu
     * @date 2019/11/27 9:56
     */
    public DataSourceType value() default DataSourceType.MASTER;
}
