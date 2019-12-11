package com.belong.service.wechat.applet.info.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.belong.common.datasource.annotation.DataSource;
import com.belong.common.datasource.config.DataSourceType;
import com.belong.common.dozer.service.IGenerator;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import com.belong.service.wechat.applet.info.mapper.WxUserInfoMapper;
import com.belong.service.wechat.applet.info.service.IWxUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @Description: 微信用户信息表
 * @Author: BelongFeng
 * @CreateDate: 2019-12-03 09:52:04
 * @UpdateUser: BelongFeng
 * @UpdateDate: 2019-12-03 09:52:04
 * @Version: 1.0
 */
@Service
@Transactional(readOnly = true)
public class WxUserInfoServiceImpl extends ServiceImpl<WxUserInfoMapper, WxUserInfoDO> implements IWxUserInfoService {
    @Resource
    protected IGenerator generator;
    /**
     * 查询WxUserInfoDO列表
     *
     * @param WxUserInfoDO
     * @return WxUserInfoDO集合
     */
    @Override
    public IPage<WxUserInfoDO> selectWxUserInfoDOList(IPage<WxUserInfoDO> page, QueryWrapper<WxUserInfoDO> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }
    @DataSource(DataSourceType.MASTER)
    @Override
    public int getMaster(String openId) {
        return baseMapper.update(WxUserInfoDO.builder().unionId(openId).build(),new QueryWrapper<>(WxUserInfoDO.builder().openId(openId).build()));
    }
    @DataSource(DataSourceType.SLAVE)
    @Override
    public int getSlave(String openId) {
        return baseMapper.update(WxUserInfoDO.builder().unionId(openId).build(),new QueryWrapper<>(WxUserInfoDO.builder().openId(openId).build()));
    }
}
