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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import javax.swing.GroupLayout;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Repository;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

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
                logger.info("Backing up file: " + persistenceFilename);
                //FileUtils.moveFile(persistenceFile, new File(metaInfDir, persistenceFilename + ".orig"));
                FileUtils.moveFile(persistenceFile, new File(srcDir, persistenceFilename + ".orig"));
            } else {
                logger.info("Creating file: " + persistenceFilename);
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

    public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {

        File file = new File("C:\\Documents and Settings\\Administrador\\Mis documentos\\NetBeansProjects\\ejemplo-modelo\\pom.xml");
        Reader reader = new FileReader(file);
        try {
            MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
            Model model = xpp3Reader.read(reader);

            String group = model.getGroupId();
            String artifact = model.getArtifactId();
            Dependency d = new Dependency();
            d.setGroupId("com.xtesoft.xtecuannet.framework");
            d.setArtifactId("xtecuannet-framework-model-mvn");
            d.setVersion("1.0.2-SNAPSHOT");
            model.addDependency(d);
            
            Repository repo = new Repository();
            repo.setName("Xtecuan.org repository");
            repo.setId("xtecuan.org");
            repo.setUrl("http://xtecuan.org/xtecuannet_framework/repository");
            model.addRepository(repo);
            
            
            MavenXpp3Writer xpp3Writer = new MavenXpp3Writer();
            xpp3Writer.write(new FileWriter(file), model);
            

            System.out.println(group);
            System.out.println(artifact);
        } finally {
            reader.close();
        }
    }
}
