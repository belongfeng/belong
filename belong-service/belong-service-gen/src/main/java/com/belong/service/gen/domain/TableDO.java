package com.belong.service.gen.domain;

import java.util.List;

/**

* @Description:    表数据
* @Author:         fengyu
* @CreateDate:     2019/11/27 16:37
* @UpdateUser:     fengyu
* @UpdateDate:     2019/11/27 16:37
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class TableDO {
    // 表的名称
    private String tableName;
    // 表的备注
    private String comments;
    // 表的主键
    private ColumnDO pk;
    // 表的列名(不包含主键)
    private List<ColumnDO> columns;
    // 表的列名(不包含主键)
    private List<ColumnDO> allColumns;
    // 类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    // 类名(第一个字母小写)，如：sys_user => sysUser
    private String classname;

    public List<ColumnDO> getAllColumns() {
        return allColumns;
    }

    public void setAllColumns(List<ColumnDO> allColumns) {
        this.allColumns = allColumns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ColumnDO getPk() {
        return pk;
    }

    public void setPk(ColumnDO pk) {
        this.pk = pk;
    }

    public List<ColumnDO> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnDO> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "TableDO{" + "tableName='" + tableName + '\'' + ", comments='" + comments + '\'' + ", pk=" + pk
                + ", columns=" + columns + ", className='" + className + '\'' + ", classname='" + classname + '\''
                + '}';
    }
}
