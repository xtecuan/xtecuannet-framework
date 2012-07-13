/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler;

import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author lcordero
 */
public class ProjectModelClasspathFiller {

    private static Logger logger = Logger.getLogger(ProjectModelClasspathFiller.class);
    public static final String NBPROJECT_FOLDER = "nbproject";
    public static final String NBPROJECT_PROP_FILE = "project.properties";
    private String CLASSPATH = "javac.classpath=\\\n"
            + "    ${libs.Jackson-2.0.classpath}:\\\n"
            + "    ${libs.spring-framework-3.0.6.RELEASE-lib.classpath}:\\\n"
            + "    ${libs.apache-commons.classpath}:\\\n"
            + "    ${libs.apache-commons-exec-1.1.classpath}:\\\n"
            + "    ${libs.jdt-compiler-3.1.1.classpath}:\\\n"
            + "    ${libs.jasperreports-3.7.6.classpath}:\\\n"
            + "    ${libs.iText-2.1.7.classpath}:\\\n"
            + "    ${libs.xtecuannet-framework.classpath}:\\\n"
            + "    ${libs.log4j-1.2.15.classpath}:\\\n"
            + "    ${libs.groovy-all-1.7.5.classpath}:\\\n"
            + "    ${libs.Java-EE-GlassFish-v3.classpath}:\\\n"
            + "    ${libs.poi-3.6-20091214.classpath}:\\\n"
            + "    ${libs.eclipselinkmodelgen.classpath}:\\\n"
            + "    ${libs.eclipselink.classpath}";

    public void patchProjectProperties() {

        File pathApp = FillerUtils.config.getPathApp();

        File nbprojectFolder = new File(pathApp, NBPROJECT_FOLDER);

        File nbprojectPropFile = new File(nbprojectFolder, NBPROJECT_PROP_FILE);

        File nbprojectPropFileBKP = new File(nbprojectFolder, NBPROJECT_PROP_FILE + ".orig");

        try {

            FileUtils.moveFile(nbprojectPropFile, nbprojectPropFileBKP);
            List lines = FileUtils.readLines(nbprojectPropFileBKP, "UTF-8");
            lines.add(CLASSPATH);

            FileUtils.writeLines(nbprojectPropFile, lines, "\n");

            logger.info("Archivo: " + nbprojectPropFile.getPath());


        } catch (Exception e) {

            logger.error("Error: ", e);
        }

    }
}
