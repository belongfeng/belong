package com.belong.common.exception.file;


import com.belong.common.exception.base.BaseException;

/**
 * @Description: 文件信息异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:50
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:50
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
