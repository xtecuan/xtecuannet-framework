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
public final class ConfigurationPropsFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "configurationProperties.ftl";
    private static Logger logger = Logger.getLogger(ConfigurationPropsFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);


        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));



        try {
            String configProps = FillerUtils.config.getConfigPropsName();
            logger.info("Creating: " + configProps);
            File srcDir = new File(FillerUtils.config.getPathApp(), "src");
            File configPropsFile = new File(srcDir, configProps);
            if (!configPropsFile.exists()) {
                Writer out = new FileWriter(configPropsFile);
                template.process(root, out);
                out.flush();
            } else {
                logger.info("File: " + configPropsFile.getName() + " already exists!!!");
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
