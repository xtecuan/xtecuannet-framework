/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler;

import com.xtesoft.xtecuannet.framework.templater.filler.interfaces.TemplaterFiller;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.JavaNoPKFilter;
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
public final class PomXmlModelEnhancerFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "pomXmlModel.ftl";
    private static Logger logger = Logger.getLogger(PomXmlModelEnhancerFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);

        File entitiesPath = FillerUtils.config.getEntitiesPath();



        File[] entities = entitiesPath.listFiles(new JavaNoPKFilter());
        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));
        root.put("entities", entities);

        try {
            String persistenceFilename = FillerUtils.config.getJpaConfigFile();
            logger.info("Creating: " + persistenceFilename);
            File srcDir = new File(FillerUtils.config.getPathApp(), "src");
//            File metaInfDir = new File(srcDir, "META-INF");
//            File persistenceFile = new File(metaInfDir, persistenceFilename);
            File persistenceFile = new File(srcDir, persistenceFilename);
            if (persistenceFile.exists()) {
                logger.info("File: " + persistenceFile.getName() + " already exists!!!");
                logger.info("Backing up file: "+persistenceFilename);
                //FileUtils.moveFile(persistenceFile, new File(metaInfDir, persistenceFilename + ".orig"));
                FileUtils.moveFile(persistenceFile, new File(srcDir, persistenceFilename + ".orig"));
            } else {
                logger.info("Creating file: "+persistenceFilename);
            }
            Writer out = new FileWriter(persistenceFile);
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
