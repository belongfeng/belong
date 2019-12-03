package com.belong.common.core.page;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: PageDataInfo
 * @Author: fengyu
 * @CreateDate: 2019/11/28 14:35
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/28 14:35
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDataInfo<T> implements Serializable {
    /**
     * 分页记录列表
     *
     * @return 分页对象记录列表
     */
    private List<T> records;


    /**
     * 当前满足条件总行数
     *
     * @return 总条数
     */
    private Long total;

    /**
     * 当前分页总页数
     *
     * @return 总页数
     */
    private Long size;


    /**
     * 当前页，默认 1
     *
     * @return 当前页
     */
    private Long current;

}
