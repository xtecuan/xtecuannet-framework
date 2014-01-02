/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.filler.impl;

import com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.FillerUtils;
import com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.filler.TemplaterFiller;
import freemarker.template.Template;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author julianr
 */
public class ServerXmlFiller implements Serializable, TemplaterFiller {

    public static final String TEMPLATE_NAME = "server.xml.ftl";

    private static final Log logger = LogFactory
            .getLog(ServerXmlFiller.class);

    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);

    }

    public String filloutTemplate(Map rootConfig) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTemplateName() {
        return TEMPLATE_NAME;
    }

}
