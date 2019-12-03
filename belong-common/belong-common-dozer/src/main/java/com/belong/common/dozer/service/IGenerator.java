package com.belong.common.dozer.service;


import java.util.List;
import java.util.Set;

/**
 * @Description: doczer转换接口
 * @Author: fengyu
 * @CreateDate: 2019/11/27 14:37
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 14:37
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IGenerator {

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return
     * @Description: 单个对象的深度复制及类型转换，vo/domain , po
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:53:24
     */
    <T, S> T convert(S s, Class<T> clz);


    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return
     * @Description: list深度复制
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:54:08
     */
    <T, S> List<T> convert(List<S> s, Class<T> clz);

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return
     * @Description: set深度复制
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:54:39
     */
    <T, S> Set<T> convert(Set<S> s, Class<T> clz);

    /**
     * @param s   数据对象
     * @param clz 复制目标类型
     * @return
     * @Description: 数组深度复制
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:54:57
     */
    <T, S> T[] convert(S[] s, Class<T> clz);
}