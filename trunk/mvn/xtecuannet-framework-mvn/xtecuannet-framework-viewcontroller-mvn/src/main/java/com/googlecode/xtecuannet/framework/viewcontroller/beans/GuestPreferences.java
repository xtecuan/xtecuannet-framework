/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.viewcontroller.beans;

import com.googlecode.xtecuannet.framework.utils.Configurator;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author xtecuan
 */
public class GuestPreferences implements Serializable {

    /*   private String theme = "redmond"; //default*/
    private String theme = Configurator.obtenerEntradaConfig("app.default.skin"); //default
    private Date date = Calendar.getInstance(new Locale("es", "SV")).getTime();

    public String getTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("theme")) {
            theme = params.get("theme");
        }

        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}