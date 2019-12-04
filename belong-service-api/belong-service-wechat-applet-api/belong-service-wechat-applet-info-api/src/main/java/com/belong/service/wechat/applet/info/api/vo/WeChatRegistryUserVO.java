package com.belong.service.wechat.applet.info.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：LionCity
 * @date ：Created in 2019/8/13 11:25
 * @description：微信小程序用户注册
 * @modified By：
 * @version: 1.0
 */
@Data
@ApiModel(value = "WeChatRegistryUserVO", description = "微信小程序登录数据")
public class WeChatRegistryUserVO implements Serializable {
    @ApiModelProperty(value = "code", required = true)
    private String code;
    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    @ApiModelProperty(name = "encryptedData", value = "包括敏感数据在内的完整用户信息的加密数据", required = true)
    private String encryptedData;
    /**
     * 不包括敏感信息的原始数据字符串，用于计算签名
     */
    @ApiModelProperty(name = "rawData", value = "不包括敏感信息的原始数据字符串", required = true)
    private String rawData;
    /**
     * 加密算法的初始向量
     */
    @ApiModelProperty(name = "iv", value = "加密算法的初始向量", required = true)
    private String iv;
    /**
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息
     */
    @ApiModelProperty(name = "signature", value = "用于校验用户信息", required = true)
    private String signature;
}
