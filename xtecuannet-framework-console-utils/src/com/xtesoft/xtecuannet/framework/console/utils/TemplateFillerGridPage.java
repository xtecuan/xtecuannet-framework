/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import com.xtesoft.xtecuannet.framework.model.entities.SecMenu;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import sv.edu.ues.siaarafia.modelo.entidades.Tblescuela;

/**
 *
 * @author xtecuan
 */
public class TemplateFillerGridPage {

    private static Logger logger = Logger.getLogger(TemplateFillerGridPage.class);

    public static void main(String[] args) throws IOException, TemplateException {
        GeneratorConfig c = new GeneratorConfig();
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(c.getTemplatesPath());
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template temp = cfg.getTemplate("gridPage.ftl");

        Map root = new HashMap();
        root.put("title", "Sample Title");

        Field[] fields = Tblescuela.class.getDeclaredFields();


//        Field id = ClassUtils.getSimplePKFieldFromEntity(SecMenu.class);
//        logger.info(id.getName());
//        logger.info("Is generated: " + ClassUtils.isSimplePKFieldGenerated(id));

        

        root.put("columnNames", fields);
        root.put("ClassUtils", getModelFor("com.xtesoft.xtecuannet.framework.console.utils.ClassUtils"));
        root.put("entityName", "Tblescuela");
        root.put("basicTemplate", c.getBasicTemplate());






        Writer out = new FileWriter(new File("/home/xtecuan/Tblescuela.xhtml"));
        temp.process(root, out);
        out.flush();

        Template temp1 = cfg.getTemplate("formPage.ftl");
        Writer out1 = new FileWriter(new File("/home/xtecuan/Tblescuela_form.xhtml"));
        temp1.process(root, out1);
        out1.flush();


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
