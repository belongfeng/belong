package com.belong.service.wechat.applet.info.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.page.PageDataInfo;
import com.belong.common.exception.base.PageException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.common.util.ServletUtils;
import com.belong.common.util.StringUtils;
import com.belong.service.wechat.applet.base.controller.AppletController;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.dto.WxUserInfoDTO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoListVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import com.belong.service.wechat.applet.info.service.IWxUserInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


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
    public ResponseVO saveWxUserInfo(@RequestBody WxUserInfoDTO wxUserInfoDTO) {
        WxUserInfoDO wxUserInfoDO = generator.convert(wxUserInfoDTO, WxUserInfoDO.class);
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
            throw new WxAppletParameterLossException();
        }
        return ResponseVO.ok(generator.convert(wxUserInfoService.getById(id), WxUserInfoVO.class));
    }

    @ApiOperation(value = "根据ID删除数据", notes = "权限标识 sys:wxUserInfo:remove")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:remove')")
    @DeleteMapping(value = "/remove/{id}")
    public ResponseVO delete(@ApiParam(required = true, value = "id") @PathVariable("id") String id) {
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
            throw new WxAppletParameterLossException();
        }
        return ResponseVO.ok(generator.convert(wxUserInfoService.getOne(new QueryWrapper<WxUserInfoDO>(WxUserInfoDO.builder().openId(openId).build())), WxUserInfoVO.class));
    }
}
