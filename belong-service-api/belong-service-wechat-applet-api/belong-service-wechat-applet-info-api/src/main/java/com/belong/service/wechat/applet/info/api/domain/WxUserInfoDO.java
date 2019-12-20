package com.belong.service.wechat.applet.info.api.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.belong.common.core.constant.Constants.DEL_FLAG_DELETE;
import static com.belong.common.core.constant.Constants.DEL_FLAG_NORMAL;


/**
 * @Description: 微信用户信息表
 * @Author: BelongFeng
 * @CreateDate: 2019-12-03 09:52:04
 */
@TableName("wx_user_info")
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxUserInfoDO {
    private static final long serialVersionUID = -1L;

    public static final String REDIS_KEY = "wx_user_info:";
    public static final String SESSION_KEY = REDIS_KEY + "session_key:";
    public static final String OPEN_ID = REDIS_KEY + "open_id:";

    /**
     * 实体编号（唯一标识）
     */
    @TableId(value = "id",type=IdType.UUID)
    private String id;

    /**
     * appid 微信平台id
     */
    @TableField("app_id")
    private String appId;
    /**
     * openid 对应平台唯一用户id
     */
    @TableField("open_id")
    private String openId;
    /**
     * union_id 跨平台统一id
     */
    @TableField("union_id")
    private String unionId;
    /**
     * 微信名称 微信名称
     */
    @TableField("nick_name")
    private String nickName;
    /**
     * 真实名称 真实名称
     */
    @TableField("real_name")
    private String realName;
    /**
     * 微信头像地址 微信头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 位置 位置信息，json存储
     */
    @TableField("addre")
    private String addre;
    /**
     * 积分 积分信息
     */
    @TableField("integral")
    private Integer integral;
    /**
     * 性别 0女，1男
     */
    @TableField("sex")
    private Integer sex;
    /**
     * 手机号 手机号
     */
    @TableField("mobile")
    private String mobile;
    /**
     * 国家 国家
     */
    @TableField("country")
    private String country;
    /**
     * 省份 省份
     */
    @TableField("province")
    private String province;
    /**
     * 城市 城市
     */
    @TableField("city")
    private String city;
    /**
     * 最近一次登录时间 最近一次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;
    /**
     * 是否可用 是否可用 是否可用1：可用0：冻结
     */
    @TableField("enabled")
    private Boolean enabled;
    /**
     * 备注 备注
     */
    @TableField("remark")
    private String remark;


    /**
     * 创建者
     */
    @TableField("create_by")
    @ApiModelProperty(value = "创建者", required = false, hidden = true)
    private String createBy;

    /**
     * 更新者
     */
    @TableField("update_by")
    @ApiModelProperty(value = "更新者", required = false, hidden = true)
    private String updateBy;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_date")
    @ApiModelProperty(value = "创建时间", required = false, hidden = true)
    private Date createDate;
    /**
     * 更新日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_date")
    @ApiModelProperty(value = "修改时间", required = false, hidden = true)
    private Date updateDate;
    /**
     * 删除标记(1:正常;0:删除;)
     */
    @TableLogic(delval = DEL_FLAG_DELETE, value = DEL_FLAG_NORMAL)
    @TableField("del_flag")
    @ApiModelProperty(value = "逻辑删除 1:正常;0:删除;", required = false, hidden = true)
    private String delFlag;

    /**
     * 乐观锁 乐观锁
     */
    @Version
    private Integer version;

    @TableField(exist = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String tokenPwd;
}
