package com.belong.common.core.constant;

/**
 * @Description: 返回值常量
 * @Author: fengyu
 * @CreateDate: 2019/11/27 15:13
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 15:13
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class ResponseCodeConstans {
    /**
     * 未授权
     */
    public static final Integer UN_AUTHORIZED = 401;

    /**
     * 未知错误
     */
    public static final Integer UN_KNOWX_ERROR = 500;

    /**
     * 拒绝访问
     */
    public static final Integer ACCESS_DENIED = 403;

    /**
     * 请求失败
     */
    public static final Integer REQUEST_FAIL = -1;

    /**
     * 请求成功
     */
    public static final Integer REQUEST_SUCCESS = 200;

    /**
     * 路径错误
     */
    public static final Integer NO_FIND_PATH = 404;

    /**
     * 服务未启动
     */
    public static final Integer SERVICE_NOT_FOUND = 20001;

}
