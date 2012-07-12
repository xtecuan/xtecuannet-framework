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
public final class RemotingServletConfigFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "remotingServlet.ftl";
    private static Logger logger = Logger.getLogger(RemotingServletConfigFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);


        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));



        try {
            String remotingFilename = FillerUtils.config.getRemotingFilename();
            logger.info("Creating: " + remotingFilename);
            File webDir = new File(FillerUtils.config.getWebappPath(), FillerUtils.config.getWebFolder());
            File webInfDir = new File(webDir, "WEB-INF");
            File remotingFile = new File(webInfDir, remotingFilename);

            if (remotingFile.exists()) {
                logger.info("File: " + remotingFile.getName() + " already exists!!!");
                FileUtils.moveFile(remotingFile, new File(webInfDir, remotingFilename + ".orig"));
            } else {
                logger.info("Creating :" + remotingFilename);
            }
            Writer out = new FileWriter(remotingFile);
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
