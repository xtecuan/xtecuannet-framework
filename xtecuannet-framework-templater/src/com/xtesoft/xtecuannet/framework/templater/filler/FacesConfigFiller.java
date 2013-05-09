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
public final class FacesConfigFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "facesConfig.ftl";
    private static Logger logger = Logger.getLogger(FacesConfigFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);


        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));



        try {
            String facesFilename = FillerUtils.config.getFacesConfigFile();
            
            
            logger.info("Creating: " + facesFilename);
            File webDir = new File(FillerUtils.config.getWebappPath(), FillerUtils.config.getWebFolder());
            File webInfDir = new File(webDir, "WEB-INF");
            File facesFile = new File(webInfDir, facesFilename);
            
            /**
             * Bundle name only
             */
            
            String bundleFilename1 = FillerUtils.config.getBundleName();
            logger.info("Creating: " + bundleFilename1);
            //File srcDir = new File(FillerUtils.config.getWebappPath(), "src/java");
            File srcDir1 = FillerUtils.config.getWebappPath();
            File bundleFile1 = new File(srcDir1, bundleFilename1);
            root.remove("bundleName");
            root.put("bundleName", FilenameUtils.getBaseName(bundleFile1.getPath()));
            //End Bundle name only

            if (facesFile.exists()) {
                logger.info("File: " + facesFile.getName() + " already exists!!!");
                FileUtils.moveFile(facesFile, new File(webInfDir, facesFilename + ".orig"));
            } else {
                logger.info("Creating :" + facesFilename);
            }
            Writer out = new FileWriter(facesFile);
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
