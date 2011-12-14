/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler;

import com.xtesoft.xtecuannet.framework.templater.filler.interfaces.TemplaterFiller;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class Log4jXmlConsoleAppenderFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "log4jXmlConsoleAppender.ftl";
    private static Logger logger = Logger.getLogger(Log4jXmlConsoleAppenderFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);


        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));



        try {
            String log4jFilename = FillerUtils.config.getLog4jConfFilename();
            logger.info("Creating: " + log4jFilename);
            File srcDir = new File(FillerUtils.config.getPathApp(), "src");
            File log4jFile = new File(srcDir, log4jFilename);
            if (!log4jFile.exists()) {
                Writer out = new FileWriter(log4jFile);
                template.process(root, out);
                out.flush();
            } else {
                logger.info("File: " + log4jFile.getName() + " already exists!!!");
            }
        } catch (Exception ex) {
            logger.error("Error al generar el archivo de template: " + TEMPLATE_NAME, ex);
        }
    }

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
