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
public final class ServiceImplFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "serviceImpl.ftl";
    private static Logger logger = Logger.getLogger(ServiceImplFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);
        File entitiesPath = FillerUtils.config.getEntitiesPath();
        File servicesPathImpl = FillerUtils.config.getServicesPathImpl();

        if (!servicesPathImpl.exists()) {
            if (servicesPathImpl.mkdirs()) {
                logger.info(servicesPathImpl.getPath() + " created");
            }
        }

        File[] entidades = entitiesPath.listFiles(new JavaNoPKFilter());
        Map root = FillerUtils.getRootConfigAsMap();

        for (int i = 0; i < entidades.length; i++) {
            File entidad = entidades[i];
            String entityName = FilenameUtils.getBaseName(entidad.getName());
            root.put("entityName", entityName);
            try {
                String serviceNameImpl = entityName + FillerUtils.config.getServiceSuffixNameImpl() + ".java";
                logger.info("Creating: " + serviceNameImpl);
                File serviceFileImpl = new File(servicesPathImpl, serviceNameImpl);
                if (!serviceFileImpl.exists()) {
                    Writer out = new FileWriter(serviceFileImpl);
                    template.process(root, out);
                    out.flush();
                } else {
                    logger.info("File: " + serviceFileImpl.getName() + " already exists!!!");
                }
            } catch (Exception ex) {
                logger.error("Error al generar el archivo de template: " + TEMPLATE_NAME, ex);
            }

        }



    }

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }
}
