package com.belong.service.gen.service.serviceimpl;

import com.belong.service.gen.mapper.GeneratorMapper;
import com.belong.service.gen.service.IGeneratorService;
import com.belong.service.gen.util.GenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @Description:
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:38
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 16:38
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Service
@Slf4j
public class GeneratorServiceImpl implements IGeneratorService {

    @Autowired
    GenUtils genUtils;
    @Autowired
    GeneratorMapper baseMapper;

    @Override
    public List<Map<String, Object>> list() {
        List<Map<String, Object>> list = baseMapper.list();
        return list;
    }


    @Override
    public byte[] generatorCode(String[] tableNames, String packageName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            // 查询表信息
            Map<String, String> table = baseMapper.get(tableName);
            // 查询列信息
            List<Map<String, String>> columns = baseMapper.listColumns(tableName);
            // 生成代码
            genUtils.generatorCode(table, columns, zip, packageName);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public int count(Map<String, Object> map) {
        return baseMapper.count(map);
    }


    @Override
    public Map<String, String> get(String tableName) {
        return baseMapper.get(tableName);
    }

    @Override
    public List<Map<String, String>> listColumns(String tableName) {
        return baseMapper.listColumns(tableName);
    }
}
