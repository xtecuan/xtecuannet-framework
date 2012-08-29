/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.modelo.enums;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public enum ResultEnum implements Serializable {

    Exito(Long.valueOf("0"), "Exito"),
    Fallo(Long.valueOf("-1"), "Fallo");
    private Long cod;
    private String des;

    private ResultEnum(Long cod, String des) {
        this.cod = cod;
        this.des = des;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
