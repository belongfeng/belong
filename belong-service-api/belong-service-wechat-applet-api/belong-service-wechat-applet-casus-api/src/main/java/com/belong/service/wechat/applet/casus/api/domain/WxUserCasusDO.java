package com.belong.service.wechat.applet.casus.api.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.belong.common.core.constant.Constants.DEL_FLAG_DELETE;
import static com.belong.common.core.constant.Constants.DEL_FLAG_NORMAL;

import java.util.Date;


/**
 * @Description: 案列
 * @Author: BelongFeng
 * @CreateDate: 2019-12-13 10:42:09
 * @UpdateDate: 2019-12-13 10:42:09
 * @Version: 1.0
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("wx_user_casus")
public class WxUserCasusDO {
    private static final long serialVersionUID = -1L;

    public static final String REDIS_KEY = "wx_user_casus:" ;
    /**
     * 实体编号（唯一标识）
     */
    @TableId
    private String id;
    /**
     * 微信用户主键
     */
    @TableField("wx_user_id")
    private String wxUserId;
    /**
     * 申请主键
     */
    @TableField("apply_id")
    private String applyId;
    /**
     * 案列介绍
     */
    @TableField("introduced")
    private String introduced;
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
     * 删除标记(0:正常;1:删除;)
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

}
