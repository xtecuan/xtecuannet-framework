/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler.utils;

import com.xtesoft.xtecuannet.framework.templater.config.TemplaterConfig;
import com.xtesoft.xtecuannet.framework.templater.constants.Constants;
import com.xtesoft.xtecuannet.framework.utils.ClassUtils;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class FillerUtils {

    public static final String CLASSPATH_FROM = "/com/xtesoft/xtecuannet/framework/templater/resources/templates/";
    private static Logger logger = Logger.getLogger(FillerUtils.class);
    public static final Class CONFIG_CLASS = TemplaterConfig.class;
    public static TemplaterConfig config = new TemplaterConfig();

    public static Template getTemplate(String templateName) {
        refreshConfig();
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

    public static void refreshConfig() {

        config = new TemplaterConfig();
    }

    public static Map getRootConfigAsMap() {

        refreshConfig();

        Map root = new HashMap();

        List<Field> fields = Constants.getOnlyFields(CONFIG_CLASS);

        for (Field field : fields) {

            Object value = ClassUtils.getPropertyFromInstance(config, field.getName());

            if (field.getType().equals(String.class) && value != null) {
                root.put(field.getName(), (String) value);
            }

            if (field.getType().equals(File.class) && value != null) {
                root.put(field.getName(), (File) value);
            }

            if (field.getType().equals(Boolean.class) && value != null) {
                root.put(field.getName(), (Boolean) value);
            }
        }
        root.put("userName", System.getProperty("user.name"));


        return root;

    }

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
}
