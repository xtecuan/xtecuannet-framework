/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.config;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author julianr
 */
public class GenericConfig implements Serializable {

    public static final String CONFIG_PATH = "./config/";

    private static final Log logger = LogFactory
            .getLog(GenericConfig.class);

    public static String getConfigPath() {
        return CONFIG_PATH;
    }

    public static Log getLogger() {
        return logger;
    }

}
