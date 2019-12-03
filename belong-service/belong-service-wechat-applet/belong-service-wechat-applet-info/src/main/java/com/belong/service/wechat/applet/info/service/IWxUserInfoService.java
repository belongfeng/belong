package com.belong.service.wechat.applet.info.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;


/**
 * @Description:    微信用户信息表
 * @Author:         BelongFeng
 * @CreateDate:      2019-12-03 09:52:04
 * @UpdateUser:     BelongFeng
 * @UpdateDate:      2019-12-03 09:52:04
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IWxUserInfoService extends IService<WxUserInfoDO> {
    /**
     * 查询WxUserInfoDO列表
     *
     * @param WxUserInfoDO
     * @return WxUserInfoDO集合
     */

    public IPage<WxUserInfoDO> selectWxUserInfoDOList(IPage<WxUserInfoDO> page, QueryWrapper<WxUserInfoDO> queryWrapper);
}
