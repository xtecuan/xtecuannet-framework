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
public final class EmptyBundleFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "EmptyBundle.ftl";
    private static Logger logger = Logger.getLogger(EmptyBundleFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);

        File servicesPath = FillerUtils.config.getServicesPath();



        File[] services = servicesPath.listFiles(new JavaNoPKFilter());
        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));
        root.put("services", services);

        try {
            String bundleFilename = FillerUtils.config.getBundleName()+".properties";
            logger.info("Creating: " + bundleFilename);
            File srcDir = new File(FillerUtils.config.getWebappPath(), "src/java");
            File bundleFile = new File(srcDir, bundleFilename);
            if (!bundleFile.exists()) {
                Writer out = new FileWriter(bundleFile);
                template.process(root, out);
                out.flush();
            } else {
                logger.info("File: " + bundleFile.getName() + " already exists!!!");
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
