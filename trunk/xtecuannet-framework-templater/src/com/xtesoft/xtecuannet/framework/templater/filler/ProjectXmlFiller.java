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
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class ProjectXmlFiller implements TemplaterFiller {

    public static final String NBPROJECT_FOLDER = "nbproject";
    public static final String NBPROJECT_XML_FILE = "project.xml";
    public static final String TEMPLATE_NAME = "project.ftl";
    private static Logger logger = Logger.getLogger(ProjectXmlFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);


        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));

        String webappPathName = FilenameUtils.getBaseName(FillerUtils.config.getWebappPath().getName());
        root.put("webappPathName", webappPathName);

        String modelProject = FilenameUtils.getBaseName(FillerUtils.config.getPathApp().getName());

        root.put("modelProject", modelProject);


        try {
            String projectXml = NBPROJECT_XML_FILE;
            logger.info("Creating: " + projectXml);
            File nbDir = new File(FillerUtils.config.getWebappPath(), NBPROJECT_FOLDER);
            File projectXmlFile = new File(nbDir, projectXml);

            if (projectXmlFile.exists()) {
                logger.info("File: " + projectXmlFile.getName() + " already exists!!!");
                FileUtils.moveFile(projectXmlFile, new File(nbDir, projectXml + ".orig"));
            } else {
                logger.info("Creating :" + projectXml);
            }
            Writer out = new FileWriter(projectXmlFile);
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
