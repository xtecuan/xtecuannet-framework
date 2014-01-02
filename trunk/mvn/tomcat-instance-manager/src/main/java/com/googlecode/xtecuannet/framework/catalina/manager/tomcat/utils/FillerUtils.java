/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import java.io.StringWriter;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author xtecuan
 */
public final class FillerUtils {

    public static final String CLASSPATH_FROM = "/com/googlecode/xtecuannet/framework/catalina/manager/tomcat/templates/";
    private static final Log logger = LogFactory
            .getLog(FillerUtils.class);

    /**
     *
     * @param templateName
     * @return a reference to Template freemarker object
     */
    public static Template getTemplate(String templateName) {

        Configuration cfg = new Configuration();
        Template temp = null;
        try {
            //cfg.setDirectoryForTemplateLoading(config.getTemplatesPath());
            cfg.setClassForTemplateLoading(FillerUtils.class, CLASSPATH_FROM);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            temp = cfg.getTemplate(templateName);
        } catch (Exception ex) {
            logger.error("Error al obtener el template de la aplicacion", ex);
        }

        return temp;

    }

    /**
     *
     * @param templateName (Name of the template example: template_correo.ftl)
     * @param classPathFrom (Name of the classPath Folder example:
     * /com/googlecode/xtecuannet/framework/catalina/manager/tomcat/templates/)
     * @return a reference to Template freemarker object
     */
    public static Template getTemplate(String templateName, String classPathFrom) {

        Configuration cfg = new Configuration();
        Template temp = null;
        try {
            //cfg.setDirectoryForTemplateLoading(config.getTemplatesPath());
            cfg.setClassForTemplateLoading(FillerUtils.class, classPathFrom);
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            temp = cfg.getTemplate(templateName);
        } catch (Exception ex) {
            logger.error("Error al obtener el template de la aplicacion", ex);
        }

        return temp;

    }

    /**
     *
     * @param clazz (To extract static Model for freemarker)
     * @return a TemplateHashModel object
     */
    public static TemplateHashModel getModelFor(String clazz) {

        BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        TemplateHashModel classStatics = null;

        try {
            classStatics = (TemplateHashModel) staticModels.get(clazz);
        } catch (Exception ex) {

            logger.error("Error processing static Model for class: " + clazz, ex);
        }

        return classStatics;

    }

    /**
     * Fillout a template con rootConfig for given name
     *
     * @param templateName
     * @param rootConfig
     * @return a String with the template filled out
     */
    public static String filloutTemplate(String templateName, Map rootConfig) {

        String result = null;

        if (!rootConfig.isEmpty()) {

            Template template = FillerUtils.getTemplate(templateName);

            try {
                StringWriter writer = new StringWriter();
                template.process(rootConfig, writer);

                result = writer.toString();

            } catch (Exception e) {
                logger.error("Error llenando el template: " + templateName, e);
            }

        } else {

            logger.error("No hay información para generar el template: " + templateName);
        }



        return result;
    }

    /**
     * Fillout a template con rootConfig for given templateName from
     * classPathFrom
     *
     * @param templateName
     * @param classPathFrom
     * @param rootConfig
     * @return a String with the template filled out
     */
    public static String filloutTemplate(String templateName, String classPathFrom, Map rootConfig) {

        String result = null;

        if (!rootConfig.isEmpty()) {

            Template template = FillerUtils.getTemplate(templateName, classPathFrom);

            try {
                StringWriter writer = new StringWriter();
                template.process(rootConfig, writer);

                result = writer.toString();

            } catch (Exception e) {
                logger.error("Error llenando el template: " + templateName, e);
            }

        } else {

            logger.error("No hay información para generar el template: " + templateName);
        }



        return result;
    }
}
