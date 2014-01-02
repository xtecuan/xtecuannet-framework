/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.filler;

import java.util.Map;

/**
 *
 * @author xtecuan
 */
public interface TemplaterFiller {

    public void filloutTemplate();

    public String filloutTemplate(Map rootConfig);

    public String getTemplateName();
}
