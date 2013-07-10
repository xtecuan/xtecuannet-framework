/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.model.results;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author xtecuan
 */
public class DateResult implements Serializable{
    private Date result;

    public DateResult() {
    }

    public Date getResult() {
        return result;
    }

    public void setResult(Date result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DateResult{" + "result=" + result + '}';
    }
    
    
}
