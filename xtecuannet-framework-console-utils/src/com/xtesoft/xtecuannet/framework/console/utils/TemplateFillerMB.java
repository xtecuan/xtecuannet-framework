/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import sv.edu.ues.siaarafia.modelo.entidades.Tblescuela;
import sv.edu.ues.siaarafia.modelo.entidades.Tblestudiante;
import sv.edu.ues.siaarafia.modelo.entidades.Tblinscripcion;

/**
 *
 * @author xtecuan
 */
public class TemplateFillerMB {

    public static void main(String[] args) throws IOException, TemplateException {
        GeneratorConfig c = new GeneratorConfig();
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(c.getTemplatesPath());
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template temp = cfg.getTemplate("ManagedBean.ftl");

        Map root = new HashMap();
        root.put("version", "1.0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        root.put("currentDatetime", sdf.format(new Date()));
        root.put("appWebPackage", c.getAppWebPackage());
        root.put("appModelPackage", c.getAppModelPackage());
        root.put("appModelPentities", c.getAppModelPentities());
        root.put("entityName", "Tblestudiante");
        root.put("appModelPservices", c.getAppModelPservices());
        root.put("userName", System.getProperty("user.name"));
        root.put("entityNameId", "MenuId");
        root.put("ClassUtils", TemplateFillerGridPage.getModelFor("com.xtesoft.xtecuannet.framework.console.utils.ClassUtils"));
        Field[] fields = Tblestudiante.class.getDeclaredFields();
        root.put("columnNames", fields);

        Writer out = new FileWriter(new File("/home/xtecuan/TblestudianteBean.java"));
        temp.process(root, out);

        out.flush();


    }
}
