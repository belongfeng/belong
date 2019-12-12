package com.belong.service.wechat.applet.info.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.page.PageDataInfo;
import com.belong.common.exception.base.PageException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.common.util.ServletUtils;
import com.belong.common.util.StringUtils;
import com.belong.service.wechat.applet.base.controller.AppletController;
import com.belong.service.wechat.applet.casus.api.feign.RemoteCopyWxUserInfoDOFService;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserInfoDOFService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoListVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import com.belong.service.wechat.applet.info.service.IWxUserInfoService;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 微信用户信息表
 * @Author: BelongFeng
 * @CreateDate: 2019-12-03 09:52:04
 * @UpdateUser: BelongFeng
 * @UpdateDate: 2019-12-03 09:52:04
 * @UpdateRemark: WxUserInfo
 * @Version: 1.0
 */
@Api(tags = "微信用户信息表")
@RestController
@AllArgsConstructor
@RequestMapping("/v1/db/wxUserInfo")
@Slf4j
public class WxUserInfoController extends AppletController {
    @Autowired
    private final IWxUserInfoService wxUserInfoService;

    @Autowired
    private final RemoteWxUserInfoDOFService remoteWxUserInfoDOFService;

    @Autowired
    private final RemoteCopyWxUserInfoDOFService remoteCopyWxUserInfoDOFService;

    @ApiOperation(value = "获取分页数据", notes = "权限标识 sys:wxUserInfo:view")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "Integer", paramType = "query")
    })
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:view')")
    @GetMapping(value = "/list")
    public ResponseVO<PageDataInfo<WxUserInfoListVO>> list() throws PageException {
        Long pageNum = ServletUtils.getParameterToLong("pageNum");
        Long pageSize = ServletUtils.getParameterToLong("pageSize");
        if (StringUtils.isNull(pageNum) || StringUtils.isNull(pageSize)) {
            throw new PageException("请填写分页参数！");
        }
        return ResponseVO.ok(Ipage2PageDataInfo(wxUserInfoService.page(startPage(pageNum, pageSize), new QueryWrapper<WxUserInfoDO>().orderByDesc("create_date")), WxUserInfoListVO.class));
    }

    @ApiOperation(value = "保存或修改数据", notes = "权限标识 sys:wxUserInfo:edit")
    @PostMapping(value = "/saveOrUpdate")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:edit')")
    public ResponseVO saveOrUpdate(@RequestBody WxUserInfoVO wxUserInfoVO) {
        WxUserInfoDO wxUserInfoDO = generator.convert(wxUserInfoVO, WxUserInfoDO.class);
        if (wxUserInfoService.saveOrUpdate(wxUserInfoDO)) {
            return ResponseVO.ok();
        }
        return ResponseVO.failed();

    }

    @ApiOperation(value = "根据ID获取详情", notes = "权限标识 sys:wxUserInfo:view")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:view')")
    @GetMapping(value = "/get/{id}")
    public ResponseVO<WxUserInfoVO> get(@ApiParam(required = true, value = "id") @PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            throw new WxAppletParameterLossException(new String[]{"id"});
        }
        return ResponseVO.ok(generator.convert(wxUserInfoService.getById(id), WxUserInfoVO.class));
    }

    @ApiOperation(value = "根据ID删除数据", notes = "权限标识 sys:wxUserInfo:remove")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:remove')")
    @GetMapping(value = "/remove/{id}")
    public ResponseVO remove(@ApiParam(required = true, value = "id") @PathVariable("id") String id) {
        if (wxUserInfoService.removeById(id)) {
            return ResponseVO.ok();
        }
        return ResponseVO.failed();
    }

    @ApiOperation(value = "根据openId获取用户信息", notes = "权限标识 sys:wxUserInfo:view")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:view')")
    @GetMapping(value = "/getWxUserInfoByOpenId/{openId}")
    public ResponseVO<WxUserInfoVO> getWxUserInfoByOpenId(@ApiParam(required = true, value = "openId") @PathVariable("openId") String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new WxAppletParameterLossException(new String[]{"openId"});
        }
        return ResponseVO.ok(generator.convert(wxUserInfoService.getOne(new QueryWrapper<WxUserInfoDO>(WxUserInfoDO.builder().openId(openId).build())), WxUserInfoVO.class));
    }

    @Transactional(readOnly = false)
    @ApiOperation(value = "多数据源事务切换", notes = "权限标识 sys:wxUserInfo:view")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:view')")
    @GetMapping(value = "/tran/{openId}")
    public ResponseVO<Map<String, Object>> tran(@ApiParam(required = true, value = "openId") @PathVariable("openId") String openId) {
        if (StringUtils.isEmpty(openId)) {
            throw new WxAppletParameterLossException(new String[]{"openId"});
        }
        Map<String, Object> map = new HashMap<>();
        map.put("1", wxUserInfoService.getMaster(openId));
        map.put("2", wxUserInfoService.getSlave(openId));
        return ResponseVO.ok(map);
    }

    @LcnTransaction
    //@Transactional
    @ApiOperation(value = "测试txlcn事务", notes = "权限标识 sys:wxUserInfo:view")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:view')")
    @GetMapping(value = "/txlcn/{oneId}/{twoId}")
    public ResponseVO<Map<String, Object>> tran(@ApiParam(required = true, value = "oneId") @PathVariable("oneId") String oneId, @ApiParam(required = true, value = "twoId") @PathVariable("twoId") String twoId) {
        Map<String, Object> map = new HashMap<>();
        ResponseVO responseVO = null;
        responseVO = remoteCopyWxUserInfoDOFService.remove(twoId);
        ResponseVO responseVO1 = null;
        if (responseVO.getCode().equals(200)) {
            System.out.println("事务参与方执行完成了");
            responseVO1 = remoteWxUserInfoDOFService.remove(oneId);
        }
        map.put("1", responseVO);
        map.put("2", responseVO1);
        return ResponseVO.ok(map);
    }
}
