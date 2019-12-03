package com.belong.common.datasource.config;

/**
 * @Description: 数据源分类
 * @Author: fengyu
 * @CreateDate: 2019/11/20 14:31
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/20 14:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public enum DataSourceType {
    /**
     * 主库，用于写库
     */
    MASTER,

    /**
     * 从库,用于读库
     */
    SLAVE
}
