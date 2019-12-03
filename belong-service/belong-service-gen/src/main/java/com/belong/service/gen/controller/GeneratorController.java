package com.belong.service.gen.controller;

import com.belong.service.gen.service.IGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 代码生成
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:37
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 16:37
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@RequestMapping("/gen")
@Controller
public class GeneratorController {
    @Autowired
    private IGeneratorService generatorService;

    @RequestMapping()
    String generator() {
        return "generator/list";
    }

    @ResponseBody
    @GetMapping("/list")
    List<Map<String, Object>> list() {
        List<Map<String, Object>> list = generatorService.list();
        return list;
    }

    @RequestMapping("/code/{tableName}")
    public void code(HttpServletRequest request, HttpServletResponse response,
                     @PathVariable("tableName") String tableName) throws IOException {
        String[] tableNames = new String[]{tableName};
        byte[] data = generatorService.generatorCode(tableNames, null);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    @RequestMapping("/batchCode")
    public void batchCode(HttpServletRequest request, HttpServletResponse response, String tables, String packageName) throws IOException {
        byte[] data = generatorService.generatorCode(tables.split(","), packageName);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
        IOUtils.closeQuietly(response.getOutputStream());
    }
}
