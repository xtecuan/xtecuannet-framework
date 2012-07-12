/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler;

import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author lcordero
 */
public class ProjectWebClasspathFiller {

    private static Logger logger = Logger.getLogger(ProjectWebClasspathFiller.class);
    public static final String NBPROJECT_FOLDER = "nbproject";
    public static final String NBPROJECT_PROP_FILE = "project.properties";
    private static String CLASSPATH_PROJECT_PROPS = "javac.classpath=\\\n"
            + "    ${libs.apache-commons.classpath}:\\\n"
            + "    ${libs.primefaces-3.0.0.classpath}:\\\n"
            + "    ${libs.jasperreports-3.7.6.classpath}:\\\n"
            + "    ${libs.poi-3.6-20091214.classpath}:\\\n"
            + "    ${libs.log4j-1.2.15.classpath}:\\\n"
            + "    ${libs.apache-commons-exec-1.1.classpath}:\\\n"
            + "    ${libs.xtecuannet-framework.classpath}:\\\n"
            + "    ${libs.groovy-all-1.7.5.classpath}:\\\n"
            + "    ${libs.jdt-compiler-3.1.1.classpath}:\\\n"
            + "    ${libs.jfreechart-10.1.12.classpath}:\\\n"
            + "    ${libs.spring-framework-3.0.6.RELEASE-lib.classpath}:\\\n"
            + "    ${libs.Jackson-2.0.classpath}:\\\n"
            + "    ${libs.xtecuannet-framework-viewcontroller-services.classpath}:\\\n"
            + "    ${libs.iText-2.1.7.classpath}:\\\n"
            + "    ${reference.${modelProject}.jar}:\\\n"
            + "    ${libs.primafaces-utils-springframework-nbsupport.classpath}";
    private static String CLASSPATH_MODEL_PROJECT_DEF = "project.${modelProject}=../${modelProject}";
    private static String CLASSPATH_MODEL_PROJECT_REF = "reference.${modelProject}.jar=${project.${modelProject}}/dist/${modelProject}.jar";

    public void patchProjectProperties() {

        File webappPath = FillerUtils.config.getWebappPath();

        File nbprojectFolder = new File(webappPath, NBPROJECT_FOLDER);

        File nbprojectPropFile = new File(nbprojectFolder, NBPROJECT_PROP_FILE);

        File nbprojectPropFileBKP = new File(nbprojectFolder, NBPROJECT_PROP_FILE + ".orig");

        File modelProjectFolder = FillerUtils.config.getPathApp();

        try {

            FileUtils.moveFile(nbprojectPropFile, nbprojectPropFileBKP);
            List lines = FileUtils.readLines(nbprojectPropFileBKP, "UTF-8");


            String modelProject = FilenameUtils.getBaseName(modelProjectFolder.getName());

            lines.add(CLASSPATH_MODEL_PROJECT_DEF.replace("${modelProject}", modelProject));
            lines.add(CLASSPATH_MODEL_PROJECT_REF.replace("${modelProject}", modelProject));
            lines.add(CLASSPATH_PROJECT_PROPS.replace("${modelProject}", modelProject));

            FileUtils.writeLines(nbprojectPropFile, lines, "\n");

            logger.info("Archivo: " + nbprojectPropFile.getPath());


        } catch (Exception e) {

            logger.error("Error: ", e);
        }



    }

    public static void main(String[] args) {
        File modelProjectFolder = FillerUtils.config.getPathApp();
        String modelProject = FilenameUtils.getBaseName(modelProjectFolder.getName());

        String result = CLASSPATH_PROJECT_PROPS.replace("${modelProject}", modelProject);


        logger.info(result);


        String result1 = CLASSPATH_MODEL_PROJECT_DEF.replace("${modelProject}", modelProject);

        logger.info(result1);


        String result2 = CLASSPATH_MODEL_PROJECT_REF.replace("${modelProject}", modelProject);


        logger.info(result2);





    }
}
