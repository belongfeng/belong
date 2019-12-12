package com.belong.service.wechat.applet.casus.controller;

import com.belong.common.core.base.ResponseVO;
import com.belong.service.wechat.applet.base.controller.AppletController;
import com.belong.service.wechat.applet.casus.service.ICopyWxUserInfoService;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
@RequestMapping("/v1/db/copyWxUserInfo")
@Slf4j
public class WxUserInfoController extends AppletController {
    @Autowired
    private final ICopyWxUserInfoService wxUserInfoService;


    @LcnTransaction
    //@Transactional
    @ApiOperation(value = "根据ID删除数据", notes = "权限标识 sys:wxUserInfo:remove")
    //@PreAuthorize("hasAuthority('sys:wxUserInfo:remove')")
    @GetMapping(value = "/remove/{id}")
    public ResponseVO remove(@ApiParam(required = true, value = "id") @PathVariable("id") String id) {
        if (wxUserInfoService.removeById(id)) {
            return ResponseVO.ok();
        }
        return ResponseVO.failed();
    }
}
