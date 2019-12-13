package com.belong.service.wechat.applet.casus.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.belong.service.wechat.applet.casus.api.domain.WxUserCasusDO;


/**
 * @Description:     案列
 * @Author:          BelongFeng
 * @CreateDate:      2019-12-13 10:42:09
 * @UpdateDate:      2019-12-13 10:42:09
 * @Version: 1.0
 */
public interface IWxUserCasusService extends IService<WxUserCasusDO> {
    /**
     * 查询WxUserCasusDO列表
     *
     * @param WxUserCasusDO
     * @return WxUserCasusDO集合
     */

    public IPage<WxUserCasusDO> selectWxUserCasusDOList(IPage<WxUserCasusDO> page, QueryWrapper<WxUserCasusDO> queryWrapper);
}
