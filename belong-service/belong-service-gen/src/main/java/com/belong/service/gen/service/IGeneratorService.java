package com.belong.service.gen.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: IGeneratorService
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:38
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 16:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface IGeneratorService {
    List<Map<String, Object>> list();

    byte[] generatorCode(String[] tableNames, String packageName);

    int count(Map<String, Object> map);


    Map<String, String> get(String tableName);

    List<Map<String, String>> listColumns(String tableName);
}
