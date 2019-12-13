package com.belong.service.wechat.applet.casus.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.belong.service.wechat.applet.casus.api.domain.WxUserCasusDO;
import com.belong.service.wechat.applet.casus.mapper.WxUserCasusMapper;
import com.belong.service.wechat.applet.casus.service.IWxUserCasusService;
import org.springframework.stereotype.Service;

/**
 * @Description:     案列
 * @Author:          BelongFeng
 * @CreateDate:      2019-12-13 10:42:09
 * @UpdateDate:      2019-12-13 10:42:09
 * @Version: 1.0
 */
@Service
public class WxUserCasusServiceImpl extends ServiceImpl<WxUserCasusMapper, WxUserCasusDO> implements IWxUserCasusService {
    /**
     * 查询WxUserCasusDO列表
     *
     * @param WxUserCasusDO
     * @return WxUserCasusDO集合
     */
    @Override
    public IPage<WxUserCasusDO> selectWxUserCasusDOList(IPage<WxUserCasusDO> page, QueryWrapper<WxUserCasusDO> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }
}
