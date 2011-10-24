/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.xconfigurator.utils;

import java.io.File;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author xtecuan
 */
public final class LoggerUtilPlus {

    public static final String FILE_CONFIG = "log4j.xml";

    /**
     * 
     * @param loggerClass
     * @return 
     */
    public static Logger getLogger(Class<?> loggerClass) {

        DOMConfigurator.configure(getUrl(null));

        return Logger.getLogger(loggerClass);
    }

    /**
     * 
     * @param loggerClass
     * @param fileUrlConfig
     * @return 
     */
    public static Logger getLogger(Class<?> loggerClass, String fileUrlConfig) {

        DOMConfigurator.configure(getUrl(fileUrlConfig));

        return Logger.getLogger(loggerClass);
    }

    /**
     * 
     * @param loggerClass
     * @param filename
     * @return 
     */
    public static Logger getLogger(Class<?> loggerClass, File file) {
        DOMConfigurator.configure(file.getPath());
        return Logger.getLogger(loggerClass);
    }

    /**
     * 
     * @param name
     * @return 
     */
    public static URL getUrl(String name) {

        if (name == null) {

            return Thread.currentThread().getContextClassLoader().getResource(FILE_CONFIG);
        } else {
            return Thread.currentThread().getContextClassLoader().getResource(name);
        }
    }
}
