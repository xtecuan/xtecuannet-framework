/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import com.xtesoft.xtecuannet.framework.model.entities.SecMenu;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class TemplateFillerHtmlFile {

    private static Logger logger = Logger.getLogger(TemplateFillerHtmlFile.class);

    public static void main(String[] args) throws IOException, TemplateException {
        GeneratorConfig c = new GeneratorConfig();
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(c.getTemplatesPath());
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template temp = cfg.getTemplate("gridPage.ftl");

        Map root = new HashMap();
        root.put("title", "Sample Title");

        Field[] fields = SecMenu.class.getDeclaredFields();


        Field id = ClassUtils.getSimplePKFieldFromEntity(SecMenu.class);

        logger.info(id.getName());

        logger.info("Is generated: "+ClassUtils.isSimplePKFieldGenerated(id));

        root.put("columnNames", fields);






        Writer out = new FileWriter(new File("/home/xtecuan/SecMenu.html"));
        temp.process(root, out);

        out.flush();


    }
}
