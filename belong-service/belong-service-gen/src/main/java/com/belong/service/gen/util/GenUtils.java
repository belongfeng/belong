package com.belong.service.gen.util;


import com.belong.common.util.date.DateUtils;
import com.belong.service.gen.config.Constant;
import com.belong.service.gen.config.GeneratorProperties;
import com.belong.service.gen.domain.ColumnDO;
import com.belong.service.gen.domain.TableDO;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description: 代码生成器 工具类
 * @Author: fengyu
 * @CreateDate: 2019/11/27 16:46
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 16:46
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class GenUtils {
    protected static final Logger log = LoggerFactory.getLogger(GenUtils.class);

    @Setter
    private GeneratorProperties properties;

    public GenUtils(GeneratorProperties properties) {
        this.properties = properties;
    }

    /**
     * 方法实现说明:获取模板
     *
     * @param
     * @return java.util.List<java.lang.String>
     * @throws
     * @author fengyu
     * @date 2019/11/27 16:48
     */
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("/templates/generator/DTO.java.vm");
        templates.add("/templates/generator/VO.java.vm");
        templates.add("/templates/generator/ListVO.java.vm");
        templates.add("/templates/generator/DO.java.vm");
        templates.add("/templates/generator/Mapper.java.vm");
        templates.add("/templates/generator/Mapper.xml.vm");
        templates.add("/templates/generator/Service.java.vm");
        templates.add("/templates/generator/ServiceImpl.java.vm");
        templates.add("/templates/generator/Controller.java.vm");
        templates.add("/templates/generator/menu.sql.vm");
        return templates;
    }

    /**
     * 方法实现说明:生成代码
     *
     * @param table
     * @param columns
     * @param zip
     * @param packageName
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/11/27 16:46
     */
    public void generatorCode(Map<String, String> table, List<Map<String, String>> columns,
                              ZipOutputStream zip, String packageName) {
        // 配置信息
        Map<String, String> config = getConfig();
        // 表信息
        TableDO tableDO = new TableDO();
        tableDO.setTableName(table.get("tableName"));
        tableDO.setComments(table.get("tableComment").replace("undefined", "无"));
        // 表名转换成Java类名
        String className = GenUtils.tableToJava(tableDO.getTableName(), config.get("tablePrefix"), config.get("autoRemovePre"));
        tableDO.setClassName(className);
        tableDO.setClassname(StringUtils.uncapitalize(className));
        // 列信息
        List<ColumnDO> columsList = new ArrayList<>();
        List<ColumnDO> allColumsList = new ArrayList<>();
        Set<String> dataTypes = new HashSet<>(), columnNames = new HashSet<>();
        List<String> baseColumnNames = Arrays.asList("create_date", "update_date", "del_flag", "id","version");
        for (Map<String, String> column : columns) {
            columnNames.add(column.get("columnName"));
            ColumnDO columnDO = new ColumnDO();
            columnDO.setColumnName(column.get("columnName"));
            columnDO.setDataType(column.get("dataType"));
            columnDO.setComments(column.get("columnComment"));
            columnDO.setExtra(column.get("extra"));

            // 列名转换成Java属性名
            String attrName = GenUtils.columnToJava(columnDO.getColumnName());
            columnDO.setAttrName(attrName);
            columnDO.setAttrname(StringUtils.uncapitalize(attrName));

            // 列的数据类型，转换成Java类型
            String attrType = config.get(columnDO.getDataType());
            columnDO.setAttrType(attrType);

            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
                tableDO.setPk(columnDO);
            }

            allColumsList.add(columnDO);
            dataTypes.add(column.get("dataType"));
        }
        tableDO.setAllColumns(allColumsList);
        for (Map<String, String> column : columns) {
            columnNames.add(column.get("columnName"));
            if (baseColumnNames.contains(column.get("columnName"))) {
                continue;
            }
            ColumnDO columnDO = new ColumnDO();
            columnDO.setColumnName(column.get("columnName"));
            columnDO.setDataType(column.get("dataType"));
            columnDO.setComments(column.get("columnComment"));
            columnDO.setExtra(column.get("extra"));

            // 列名转换成Java属性名
            String attrName = GenUtils.columnToJava(columnDO.getColumnName());
            columnDO.setAttrName(attrName);
            columnDO.setAttrname(StringUtils.uncapitalize(attrName));

            // 列的数据类型，转换成Java类型
            String attrType = config.get(columnDO.getDataType());
            columnDO.setAttrType(attrType);

            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
                tableDO.setPk(columnDO);
            }

            columsList.add(columnDO);
            dataTypes.add(column.get("dataType"));
        }
        tableDO.setColumns(columsList);

        // 没主键，则第一个字段为主键
        if (tableDO.getPk() == null) {
            tableDO.setPk(tableDO.getColumns().get(0));
        }

        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        // 封装模板数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableDO.getTableName());
        map.put("comments", tableDO.getComments());
        map.put("tableComments", tableDO.getComments());
        map.put("pk", tableDO.getPk());
        map.put("className", tableDO.getClassName());
        map.put("classname", tableDO.getClassname());
        String pack = config.get("package");
        if (packageName != null) {
            pack = packageName;
        }
        map.put("package", pack);
        map.put("pathName", pack.substring(pack.lastIndexOf(".") + 1));
        map.put("columns", tableDO.getColumns());
        map.put("allColumns", tableDO.getAllColumns());
        map.put("author", config.get("author"));
        map.put("email", config.get("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
        // 字段特性
        map.put("hasBigDecimal", dataTypes.contains("decimal"));
        map.put("hasCreateBy", columnNames.contains("create_by"));
        //时间类型的包含3种，date、datetime、timestamp
        if (dataTypes.contains("date") || dataTypes.contains("datetime") || dataTypes.contains("timestamp")) {
            map.put("hasDatetime", true);
        }
        map.put("hasVersion", columnNames.contains("version"));
        VelocityContext context = new VelocityContext(map);

        // 获取模板列表
        List<String> templates = GenUtils.getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template, tableDO.getClassname(), tableDO.getClassName(),
                        pack, config)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.warn(e.getMessage());
                log.info("渲染模板失败，表名：" + tableDO.getTableName());
                //throw new IFastException(EnumErrorCode.genRenderTemplateError.getCodeStr());
            }
        }
    }

    /**
     * 获取配置信息
     * 方法实现说明:
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @throws
     * @author fengyu
     * @date 2019/11/27 16:46
     */
    public Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        config.putAll(properties.getGenerator());
        return config;
    }

    /**
     * 方法实现说明:列名转换成Java属性名
     *
     * @param columnName
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/27 16:47
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 方法实现说明:表名转换成Java类名
     *
     * @param tableName
     * @param tablePrefix
     * @param autoRemovePre
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/27 16:47
     */
    public static String tableToJava(String tableName, String tablePrefix, String autoRemovePre) {
        if (Constant.Generator.AUTO_REOMVE_PRE.equals(autoRemovePre)) {
            tableName = tableName.substring(tableName.indexOf("_") + 1);
        }
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }

        return columnToJava(tableName);
    }

    /**
     * 方法实现说明:获取文件名
     *
     * @param template
     * @param classname
     * @param className
     * @param packageName
     * @param config
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/11/27 16:47
     */
    public static String getFileName(String template, String classname, String className, String packageName, Map<String, String> config) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("Controller.java.vm")) {
            String packController = config.get("controller");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "Controller.java";
            } else {
                return "controller" + File.separator + className + "Controller.java";
            }
        }


        if (template.contains("Service.java.vm")) {
            String packController = config.get("service");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + "I" + className + "Service.java";
            } else {
                return "service" + File.separator + "I" + className + "Service.java";
            }
        }
        if (template.contains("DO.java.vm")) {
            String packController = config.get("do");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "DO.java";
            } else {
                return "domain" + File.separator + className + "DO.java";
            }
        }
        if (template.contains("DTO.java.vm")) {
            String packController = config.get("dto");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "DTO.java";
            } else {
                return "dto" + File.separator + className + "DTO.java";
            }
        }
        if (template.contains("ListVO.java.vm")) {
            String packController = config.get("vo");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "ListVO.java";
            } else {
                return "vo" + File.separator + className + "ListVO.java";
            }
        }

        if (template.contains("VO.java.vm")) {
            String packController = config.get("vo");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "VO.java";
            } else {
                return "vo" + File.separator + className + "VO.java";
            }
        }

        if (template.contains("Mapper.java.vm")) {
            String packController = config.get("mapper");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "Mapper.java";
            } else {
                return "mapper" + File.separator + className + "Mapper.java";
            }
        }
        if (template.contains("ServiceImpl.java.vm")) {
            String packController = config.get("serviceImpl");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "ServiceImpl.java";
            } else {
                return "serviceImpl" + File.separator + className + "ServiceImpl.java";
            }
        }


        if (template.contains("Mapper.xml.vm")) {
            String packController = config.get("mapperXml");
            if (StringUtils.isNotBlank(packController)) {
                packController += packController.replace(".", File.separator) + File.separator;
                return packController + className + "Mapper.xml";
            } else {
                return "mapperXml" + File.separator + className + "Mapper.xml";
            }
        }
        if (template.contains("menu.sql.vm")) {
            return className.toLowerCase() + "_menu.sql";
        }


        if (template.contains("list.html.vm")) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator + packageName
                    + File.separator + classname + File.separator + classname + ".html";
        }
        if (template.contains("add.html.vm")) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator + packageName
                    + File.separator + classname + File.separator + "add.html";
        }
        if (template.contains("edit.html.vm")) {
            return "main" + File.separator + "resources" + File.separator + "templates" + File.separator + packageName
                    + File.separator + classname + File.separator + "edit.html";
        }

        if (template.contains("list.js.vm")) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js"
                    + File.separator + "appjs" + File.separator + packageName + File.separator + classname
                    + File.separator + classname + ".js";
        }
        if (template.contains("add.js.vm")) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js"
                    + File.separator + "appjs" + File.separator + packageName + File.separator + classname
                    + File.separator + "add.js";
        }
        if (template.contains("edit.js.vm")) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator + "js"
                    + File.separator + "appjs" + File.separator + packageName + File.separator + classname
                    + File.separator + "edit.js";
        }


        return null;
    }

    public static void main(String[] args) {
        String str = "表名:rf_train_trainee,备注:训练出席球员,描述:俱乐部----->训练课------>训练------->预期出席球员";
        System.out.println(str.substring(str.indexOf("备注:") + 3, str.indexOf(",描述")));
    }
}
