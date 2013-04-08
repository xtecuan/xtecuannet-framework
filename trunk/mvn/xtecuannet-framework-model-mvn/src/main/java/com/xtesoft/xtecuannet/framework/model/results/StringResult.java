/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.results;

import java.io.Serializable;

/**
 *
 * @author xtecuan
 */
public class StringResult implements Serializable{

    private String result;

    public StringResult() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "StringResult{" + "result=" + result + '}';
    }
}
