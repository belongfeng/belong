package com.belong.service.wechat.applet.casus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.core.base.BaseController;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.core.page.PageDataInfo;
import com.belong.common.exception.base.PageException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.common.util.ServletUtils;
import com.belong.common.util.StringUtils;
import com.belong.service.wechat.applet.casus.api.domain.WxUserCasusDO;
import com.belong.service.wechat.applet.casus.api.vo.WxUserCasusListVO;
import com.belong.service.wechat.applet.casus.api.vo.WxUserCasusVO;
import com.belong.service.wechat.applet.casus.service.IWxUserCasusService;
import com.codingapi.tx.annotation.TxTransaction;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


/**
 * @Description: 案列
 * @Author: BelongFeng
 * @CreateDate: 2019-12-13 10:42:09
 * @UpdateDate: 2019-12-13 10:42:09
 * @Version: 1.0
 */
@Api(tags = "案列")
@RestController
@AllArgsConstructor
@RequestMapping("/v1/db//wxUserCasus")
@Slf4j
public class WxUserCasusController extends BaseController {
    @Autowired
    private final IWxUserCasusService wxUserCasusService;


    @ApiOperation(value = "获取分页数据", notes = "权限标识 sys:wxUserCasus:view")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "Integer", paramType = "query")
    })
    @GetMapping(value = "/list")
    public ResponseVO<PageDataInfo<WxUserCasusListVO>> list() throws PageException {
        Long pageNum = ServletUtils.getParameterToLong("pageNum");
        Long pageSize = ServletUtils.getParameterToLong("pageSize");
        if (StringUtils.isNull(pageNum) || StringUtils.isNull(pageSize)) {
            throw new PageException("请填写分页参数！");
        }
        return ResponseVO.ok(Ipage2PageDataInfo(wxUserCasusService.page(startPage(pageNum, pageSize), new QueryWrapper<WxUserCasusDO>().orderByDesc("create_date")), WxUserCasusListVO.class));
    }

    @Transactional(readOnly = false)
    @ApiOperation(value = "保存或修改数据", notes = "权限标识 sys:wxUserCasus:edit")
    @PostMapping(value = "/saveOrUpdate")
    public ResponseVO saveOrUpdate(@RequestBody WxUserCasusVO wxUserCasusVO) {
        WxUserCasusDO wxUserCasusDO = generator.convert(wxUserCasusVO, WxUserCasusDO.class);
        if (wxUserCasusService.saveOrUpdate(wxUserCasusDO)) {
            return ResponseVO.ok();
        }
        return ResponseVO.failed();

    }

    @ApiOperation(value = "根据ID获取详情", notes = "权限标识 sys:wxUserCasus:view")
    @GetMapping(value = "/get/{id}")
    public ResponseVO<WxUserCasusVO> get(@ApiParam(required = true, value = "id") @PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            throw new WxAppletParameterLossException(new String[]{"id"});
        }
        return ResponseVO.ok(generator.convert(wxUserCasusService.getById(id), WxUserCasusVO.class));
    }

    @TxTransaction
    @Transactional(readOnly = false)
    @ApiOperation(value = "根据ID删除数据", notes = "权限标识 sys:wxUserCasus:remove")
    @GetMapping(value = "/remove/{id}")
    public ResponseVO remove(@ApiParam(required = true, value = "id") @PathVariable("id") String id) {

        if (wxUserCasusService.removeById(id)) {
            return ResponseVO.ok();
        }
        return ResponseVO.failed();
    }
}
