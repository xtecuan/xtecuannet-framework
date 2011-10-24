/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.xconfigurator.utils.enums;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public enum LogConfigType implements Serializable {

    AppFileAppender(Integer.valueOf("1"), "FILE", "AppExternalFile.xml"),
    AppConsoleAppender(Integer.valueOf("2"), "CONSOLE", "AppConsoleAppender.xml");
    private Integer cod;
    private String desc;
    private String template;

    private LogConfigType(Integer cod, String desc, String template) {
        this.cod = cod;
        this.desc = desc;
        this.template = template;
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

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
