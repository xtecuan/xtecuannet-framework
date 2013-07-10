/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.viewcontroller.beans.enums;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public enum MessageKeyEntry implements Serializable {

    CREATE_KEY(1, "CREATE"),
    UPDATE_KEY(2, "UPDATE"),
    DELETE_KEY(3, "DELETE"),
    FIND_KEY(4, "FIND"),
    PK_COLS_NAMES_ARRAY_KEYS(5, "PK_COLS_NAMES_ARRAY_KEYS"),
    SUCCESS_KEY(6, "SUCCESS_KEY"),
    FAILURE_KEY(7, "FAILURE"),
    SEARCH_NOT_FOUND_KEY(8, "SEARCH_NOT_FOUND_KEY");
    private Integer cod;
    private String key;

    private MessageKeyEntry(Integer cod, String key) {
        this.cod = cod;
        this.key = key;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
