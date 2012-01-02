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
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class TemplateFillerFormGenerator {

    private static Logger logger = Logger.getLogger(TemplateFillerFormGenerator.class);

//     throws IOException, TemplateException, ClassNotFoundException
    public static void main(String[] args) {
        try {
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
            root.put("appModelPservices", c.getAppModelPservices());
            root.put("userName", System.getProperty("user.name"));
            root.put("entityNameId", "MenuId");
            root.put("ClassUtils", TemplateFillerGridPage.getModelFor("com.xtesoft.xtecuannet.framework.console.utils.ClassUtils"));
            root.put("appWebBaseBean", c.getAppWebBaseBean());
            root.put("appWebViewUtils", c.getAppWebViewUtils());
            root.put("basicTemplate", c.getBasicTemplate());


            FileFilter ff = new FileFilter() {

                public boolean accept(File file) {
                    return file.getPath().endsWith(".java") && !file.getPath().contains("PK");
                }
            };

            String salida = StringUtils.replaceChars(c.getAppModelPackage(), '.', File.separatorChar);

            logger.info(salida);

            File pathEntities = new File(c.getAppModelPath(), "src" + File.separatorChar + salida + File.separatorChar + c.getAppModelPentities());

            logger.info(pathEntities.getPath());

            String salida1 = StringUtils.replaceChars(c.getAppWebPackage(), '.', File.separatorChar);
            File beansPath = new File(c.getAppWebPath(), "src" + File.separator + "java" + File.separator + salida1);

            if (beansPath.exists()) {
                logger.info(beansPath);
            }

            File[] entities = pathEntities.listFiles(ff);

            for (int i = 0; i < entities.length; i++) {
                File file = entities[i];
                logger.info("Processing: " + file.getName());
                String entityName = FilenameUtils.getBaseName(file.getName());
                logger.info(entityName);
                Field[] fields = Class.forName(c.getAppModelPackage() + "." + c.getAppModelPentities() + "." + entityName).getDeclaredFields();

                root.put("columnNames", fields);
                root.put("entityName", entityName);

                Writer out = new FileWriter(new File(beansPath, entityName + "Bean.java"));
                temp.process(root, out);

                out.flush();

//                Template temp2 = cfg.getTemplate("bundle.ftl");
//                Writer out2 = new FileWriter(new File(c.getAppWebPath(), "src" + File.separator + "java" + File.separator + entityName + "Bean.generated"));
//                temp2.process(root, out2);
//                out2.flush();

//                Template temp3 = cfg.getTemplate("formPage.ftl");
//                Writer out3 = new FileWriter(new File(c.getAppWebPath(), c.getAppWebPagesGenerated() + entityName + "_form.xhtml"));
//                temp3.process(root, out3);
//                out3.flush();


            }


//            root.put("entities", entities);
//            root.put("FilenameUtils", TemplateFillerGridPage.getModelFor("org.apache.commons.io.FilenameUtils"));
//            Template temp1 = cfg.getTemplate("springManagedBean.ftl");
//            Writer out1 = new FileWriter(new File(c.getAppWebPath(), c.getAppWebPagesGenerated() + c.getAppWebSpringfilename()));
//            temp1.process(root, out1);
//
//            out1.flush();


        } catch (Exception ex) {

            logger.error("Error", ex);
        }





    }
}
