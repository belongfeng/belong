package com.belong.service.wechat.applet.casus.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 微信用户信息表
 * @Author: BelongFeng
 * @CreateDate: 2019-12-03 09:52:04
 * @UpdateUser: BelongFeng
 * @UpdateDate: 2019-12-03 09:52:04
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "WxUserInfoDTO", description = "微信用户信息表")
public class CopyWxUserInfoDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "appid 微信平台id", required = true)
    private String appId;
    @ApiModelProperty(value = "openid 对应平台唯一用户id", required = true)
    private String openId;
    @ApiModelProperty(value = "union_id 跨平台统一id", required = true)
    private String unionId;
    @ApiModelProperty(value = "微信名称 微信名称", required = true)
    private String nickName;
    @ApiModelProperty(value = "真实名称 真实名称", required = true)
    private String realName;
    @ApiModelProperty(value = "微信头像地址 微信头像地址", required = true)
    private String avatarUrl;
    @ApiModelProperty(value = "位置 位置信息，json存储", required = true)
    private String addre;
    @ApiModelProperty(value = "积分 积分信息", required = true)
    private Integer integral;
    @ApiModelProperty(value = "性别 0女，1男", required = true)
    private Integer sex;
    @ApiModelProperty(value = "手机号 手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "国家 国家", required = true)
    private String country;
    @ApiModelProperty(value = "省份 省份", required = true)
    private String province;
    @ApiModelProperty(value = "城市 城市", required = true)
    private String city;
    @ApiModelProperty(value = "最近一次登录时间 最近一次登录时间", required = true)
    private Date lastLoginTime;
    @ApiModelProperty(value = "是否可用 是否可用 是否可用0：冻结，1：可用", required = true)
    private Boolean enabled;
    @ApiModelProperty(value = "备注 备注", required = true)
    private String remark;
}
