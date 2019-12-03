package com.belong.common.exception.file;

/**
 * @Description: 文件名称超长限制异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:50
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:50
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
