package com.belong.common.util.i18n;

import com.belong.common.util.spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @Description: 获取i18n资源文件
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:30
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:30
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class MessageUtils {
    /**
     * 方法实现说明:根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/26 17:30
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
