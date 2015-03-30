/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler.utils;

import java.sql.Types;

/**
 *
 * @author julianr
 */
public final class SQLField {

    private String columnName;
    private int columnType;
    private String coldfusionType;

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

        if (this.getColumnType() == Types.DATE) {
            this.coldfusionType = "date";
        }

        if (this.getColumnType() == Types.BOOLEAN) {
            this.coldfusionType = "boolean";
        }
    }

    public String getColdfusionType() {
        setColdfusionType();
        return coldfusionType;
    }

    @Override
    public String toString() {
        return "SQLField{" + "columnName=" + columnName + ", columnType=" + columnType + ", coldfusionType=" + getColdfusionType() + '}';
    }
}
