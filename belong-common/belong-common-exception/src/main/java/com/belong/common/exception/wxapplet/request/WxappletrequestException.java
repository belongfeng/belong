package com.belong.common.exception.wxapplet.request;

/**
 * @Classname WxappletrequestException
 * @Description TODO
 * @Date 2019/12/5 11:29
 * @Created by FengYu
 */
public class WxappletrequestException extends RuntimeException {
    private static final String DEFAULT_MSG = "接口异常！" ;

    private static final long serialVersionUID = 3885400551304383736L;

    public WxappletrequestException(String msg) {
        super(msg);
    }

    public WxappletrequestException() {
        super(DEFAULT_MSG);
    }
}
