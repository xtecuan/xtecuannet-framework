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
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class WebXmlFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "webXml.ftl";
    private static Logger logger = Logger.getLogger(WebXmlFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);


        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));



        try {
            String webXmlFilename = FillerUtils.config.getWebXmlFile();
            logger.info("Creating: " + webXmlFilename);
            File webDir = new File(FillerUtils.config.getWebappPath(), FillerUtils.config.getWebFolder());
            File webInfDir = new File(webDir, "WEB-INF");
            File webXmlFile = new File(webInfDir, webXmlFilename);

            if (webXmlFile.exists()) {
                logger.info("File: " + webXmlFile.getName() + " already exists!!!");
                FileUtils.moveFile(webXmlFile, new File(webInfDir, webXmlFilename + ".orig"));
            } else {
                logger.info("Creating :" + webXmlFilename);
            }
            Writer out = new FileWriter(webXmlFile);
            template.process(root, out);
            out.flush();
        } catch (Exception ex) {
            logger.error("Error al generar el archivo de template: " + TEMPLATE_NAME, ex);
        }
    }

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
