/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler.utils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julianr
 */
public final class SQLField {

    private String columnName;
    private int columnType;
    private String coldfusionType;
    private String javaType;
    private String javaImport;

    public SQLField(String columnName, int columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;

    }

    private void setColdfusionType() {
        this.coldfusionType = "Any";

        if (this.getColumnType() == Types.VARCHAR || this.getColumnType() == Types.CHAR || this.getColumnType() == Types.NCHAR) {

            this.coldfusionType = "String";
        }

        if (this.getColumnType() == Types.INTEGER || this.getColumnType() == Types.BIGINT || this.getColumnType() == Types.BIT
                || this.getColumnType() == Types.FLOAT || this.getColumnType() == Types.DECIMAL || this.getColumnType() == Types.DOUBLE
                || this.getColumnType() == Types.TINYINT) {
            this.coldfusionType = "numeric";
        }

        if (this.getColumnType() == Types.DATE || this.getColumnType() == Types.TIMESTAMP) {
            this.coldfusionType = "date";
        }

        if (this.getColumnType() == Types.BOOLEAN) {
            this.coldfusionType = "boolean";
        }
    }

    private void setJavaType() {

        this.javaType = "String";

        if (this.getColumnType() == Types.VARCHAR || this.getColumnType() == Types.CHAR || this.getColumnType() == Types.NCHAR) {

            this.javaType = "String";
        }

        if (this.getColumnType() == Types.INTEGER || this.getColumnType() == Types.BIT) {
            this.javaType = "Integer";
        }

        if (this.getColumnType() == Types.BIGINT) {
            this.javaType = "Long";
        }

        if (this.getColumnType() == Types.FLOAT || this.getColumnType() == Types.DECIMAL || this.getColumnType() == Types.DOUBLE) {
            this.javaType = "BigDecimal";
        }

        if (this.getColumnType() == Types.DATE || this.getColumnType() == Types.TIMESTAMP) {
            this.javaType = "Date";
        }

        if (this.getColumnType() == Types.BOOLEAN) {
            this.javaType = "Boolean";
        }

    }

    private void setJavaImport() {
        if (this.getColumnType() == Types.FLOAT || this.getColumnType() == Types.DECIMAL || this.getColumnType() == Types.DOUBLE) {

            this.javaImport = "java.math.BigDecimal";
        }

        if (this.getColumnType() == Types.DATE || this.getColumnType() == Types.TIMESTAMP) {
            this.javaImport = "java.util.Date";
        }

        if (this.getColumnType() == Types.VARCHAR || this.getColumnType() == Types.CHAR || this.getColumnType() == Types.NCHAR
                || this.getColumnType() == Types.INTEGER || this.getColumnType() == Types.BIGINT || this.getColumnType() == Types.BIT || this.getColumnType() == Types.BOOLEAN) {
            this.javaImport = "";
        }
    }

    public String getJavaType() {
        setJavaType();
        return javaType;
    }

    public String getColdfusionType() {
        setColdfusionType();
        return coldfusionType;
    }

    public String getJavaImport() {
        setJavaImport();
        return javaImport;
    }

    @Override
    public String toString() {
        return "SQLField{" + "columnName=" + columnName + ", columnType=" + columnType + ", coldfusionType=" + coldfusionType + ", javaType=" + javaType + ", javaImport=" + javaImport + '}';
    }

    public static List<SQLField> getImportsFromList(List<SQLField> fields) {

        List<SQLField> returnFields = new ArrayList<SQLField>(0);

        for (SQLField field : fields) {

            if (field != null && !field.getJavaImport().equals("") && !returnFields.contains(field)) {
                returnFields.add(field);
            }
        }

        return returnFields;
    }

}
