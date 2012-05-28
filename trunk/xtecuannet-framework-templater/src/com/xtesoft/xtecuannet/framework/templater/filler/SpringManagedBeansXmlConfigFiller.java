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
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class SpringManagedBeansXmlConfigFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "springManagedBeansXmlConfig.ftl";
    private static Logger logger = Logger.getLogger(SpringManagedBeansXmlConfigFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);

        File servicesPath = FillerUtils.config.getServicesPath();



        File[] services = servicesPath.listFiles(new JavaNoPKFilter());
        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));
        root.put("services", services);

        try {
            String springBackingBeansFilename = FillerUtils.config.getSpringBackingBeansFile();
            logger.info("Creating: " + springBackingBeansFilename);
            File srcDir = new File(FillerUtils.config.getWebappPath(), "src/java");
            File springConfigFile = new File(srcDir, springBackingBeansFilename);
            if (!springConfigFile.exists()) {
                Writer out = new FileWriter(springConfigFile);
                template.process(root, out);
                out.flush();
            } else {
                logger.info("File: " + springConfigFile.getName() + " already exists!!!");
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
