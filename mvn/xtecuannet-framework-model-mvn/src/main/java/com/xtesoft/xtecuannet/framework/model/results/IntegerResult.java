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
public class IntegerResult implements Serializable{
    
    private Integer result;

    public IntegerResult() {
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IntegerResult{" + "result=" + result + '}';
    }
    
    
}
