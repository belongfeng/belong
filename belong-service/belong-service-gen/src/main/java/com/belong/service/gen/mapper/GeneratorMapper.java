package com.belong.service.gen.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: GeneratorMapper
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:37
 * @UpdateDate: 2019/11/27 16:37
 * @Version: 1.0
 */
@Mapper
public interface GeneratorMapper {

    List<Map<String, Object>> list();


    int count(Map<String, Object> map);


    Map<String, String> get(String tableName);

    List<Map<String, String>> listColumns(String tableName);
}
