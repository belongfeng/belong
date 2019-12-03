package com.belong.common.exception.file;

/**
 * @Description: 文件名大小限制异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:50
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:50
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
