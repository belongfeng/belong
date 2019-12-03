package com.belong.common.datasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description: 动态数据源
 * @Author: fengyu
 * @CreateDate: 2019/11/20 14:31
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/20 14:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}