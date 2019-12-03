package com.belong.common.datasource.datasource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 数据源切换处理
 * @Author: fengyu
 * @CreateDate: 2019/10/22 17:37
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/10/22 17:37
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Slf4j
public class DynamicDataSourceContextHolder {

    /**
     * 方法实现说明:     使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     *
     * @author fengyu
     * @param null
     * @return
     * @exception
     * @date 2019/11/27 9:55
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 方法实现说明:获得数据源的变量
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/27 9:55
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 方法实现说明:设置数据源的变量
     *
     * @param dsType
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/27 9:55
     */
    public static void setDataSourceType(String dsType) {
        log.info("切换到{}数据源", dsType);
        CONTEXT_HOLDER.set(dsType);
    }

    /**
     * 方法实现说明:清空数据源变量
     *
     * @param
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/27 9:55
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
