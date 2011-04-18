/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

/**
 *
 * @author xtecuan
 */
public enum XConnEnum {

    Postgresql(Integer.valueOf("1"), "postgresql"),
    Mysql(Integer.valueOf("2"), "mysql");
    private Integer cod;
    private String desc;

    XConnEnum(Integer cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static XConnEnum getConfig(String name) {

        if (name.equals(Postgresql.desc)) {

            return Postgresql;
        } else if (name.equals(Mysql.desc)) {

            return Mysql;
        } else {

            return Postgresql;
        }
    }
}
