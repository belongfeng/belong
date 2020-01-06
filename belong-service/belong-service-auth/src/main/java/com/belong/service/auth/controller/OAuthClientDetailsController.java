package com.belong.service.auth.controller;

import com.belong.common.core.base.ResponseVO;
import com.belong.common.exception.wxapplet.WxAppletException;
import com.belong.service.auth.entity.OAuthClientDetails;
import com.belong.service.auth.service.OAuthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2020/1/6 10:37
*/
@Slf4j
@Validated
@RestController
@RequestMapping("/client")
public class OAuthClientDetailsController {

    @Autowired
    private OAuthClientDetailsService oAuthClientDetailsService;

    @GetMapping("/check/{clientId}")
    public boolean checkUserName(@NotBlank(message = "{required}") @PathVariable String clientId) {
        OAuthClientDetails client = this.oAuthClientDetailsService.findById(clientId);
        return client == null;
    }

    @GetMapping("/secret/{clientId}")
    //@PreAuthorize("hasAuthority('client:decrypt')")
    public ResponseVO getOriginClientSecret(@NotBlank(message = "{required}") @PathVariable String clientId) {
        OAuthClientDetails client = this.oAuthClientDetailsService.findById(clientId);
        String origin = client != null ? client.getOriginSecret() : StringUtils.EMPTY;
        return ResponseVO.ok(origin);
    }

    //@GetMapping
    //@PreAuthorize("hasAuthority('client:view')")
    //public ResponseVO oauthCliendetailsList(QueryRequest request, OAuthClientDetails oAuthClientDetails) {
    //    Map<String, Object> dataTable = StringUtil.getDataTable(this.oAuthClientDetailsService.findOAuthClientDetails(request, oAuthClientDetails));
    //    return ResponseVO.ok(dataTable);
    //}


    @PostMapping
    //@PreAuthorize("hasAuthority('client:add')")
    public void addOauthCliendetails(@Valid OAuthClientDetails oAuthClientDetails) throws WxAppletException {
        try {
            this.oAuthClientDetailsService.createOAuthClientDetails(oAuthClientDetails);
        } catch (Exception e) {
            String message = "新增客户端失败";
            log.error(message, e);
            throw new WxAppletException(message);
        }
    }

    //@DeleteMapping
    //@PreAuthorize("hasAuthority('client:delete')")
    //public void deleteOauthCliendetails(@NotBlank(message = "{required}") String clientIds) throws AKException {
    //    try {
    //        this.oAuthClientDetailsService.deleteOAuthClientDetails(clientIds);
    //    } catch (Exception e) {
    //        String message = "删除客户端失败";
    //        log.error(message, e);
    //        throw new AKException(message);
    //    }
    //}

    //@PutMapping
    //@PreAuthorize("hasAuthority('client:update')")
    //public void updateOauthCliendetails(@Valid OAuthClientDetails oAuthClientDetails) throws AKException {
    //    try {
    //        this.oAuthClientDetailsService.updateOAuthClientDetails(oAuthClientDetails);
    //    } catch (Exception e) {
    //        String message = "修改客户端失败";
    //        log.error(message, e);
    //        throw new AKException(message);
    //    }
    //}
}
