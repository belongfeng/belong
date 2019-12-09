package com.belong.common.core.base;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.belong.common.core.page.PageDataInfo;
import com.belong.common.dozer.service.IGenerator;
import com.belong.common.util.ServletUtils;
import com.belong.common.util.StringUtils;
import com.belong.common.util.date.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @Description: web层通用数据处理
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:17
 * @UpdateDate: 2019/11/27 16:17
 * @Version: 1.0
 */
@Slf4j
public class BaseController {

    @Resource
    protected IGenerator generator;

    /**
     * 方法实现说明:将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:44
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 方法实现说明:设置请求分页数据
     *
     * @param
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:43
     */
    protected Page startPage(Long pageNum, Long pageSize) {
        Page page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        return page;
    }

    /**
     * 方法实现说明:将分页实体转换成对应前端实体
     *
     * @param
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/28 14:43
     */
    protected <T> PageDataInfo<T> Ipage2PageDataInfo(IPage iPage, Class<T> tClass) {
        PageDataInfo pageDataInfo = new PageDataInfo();
        pageDataInfo.setCurrent(iPage.getCurrent());
        pageDataInfo.setRecords(generator.convert(iPage.getRecords(), tClass));
        pageDataInfo.setSize(iPage.getSize());
        pageDataInfo.setTotal(iPage.getTotal());
        return pageDataInfo;
    }
}
