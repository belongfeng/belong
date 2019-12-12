package com.belong.service.wechat.applet.casus.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.belong.service.wechat.applet.casus.api.domain.CopyWxUserInfoDO;
import com.belong.service.wechat.applet.casus.mapper.CopyWxUserInfoMapper;
import com.belong.service.wechat.applet.casus.service.ICopyWxUserInfoService;
import org.springframework.stereotype.Service;

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
public class CopyWxUserInfoServiceImpl extends ServiceImpl<CopyWxUserInfoMapper, CopyWxUserInfoDO> implements ICopyWxUserInfoService {

    @Override
    public IPage<CopyWxUserInfoDO> selectWxUserInfoDOList(IPage<CopyWxUserInfoDO> page, QueryWrapper<CopyWxUserInfoDO> queryWrapper) {
        return null;
    }
}
