/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler;

import com.xtesoft.xtecuannet.framework.templater.filler.interfaces.TemplaterFiller;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Repository;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

/**
 *
 * @author xtecuan
 */
public final class PomXmlWebEnhancerFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "pomXmlModel.ftl";
    public static final String POM_XML = "pom.xml";
    private static Logger logger = Logger.getLogger(PomXmlWebEnhancerFiller.class);
    private static final Dependency xfwm;
    private static final Repository repo_xfw;
    private static final List<Dependency> dependencies = new ArrayList<Dependency>(0);
    private static final List<Repository> repos = new ArrayList<Repository>(0);

    static {

        xfwm = new Dependency();
        xfwm.setGroupId("com.xtesoft.xtecuannet.framework");
        xfwm.setArtifactId("xtecuannet-framework-model-mvn");
        xfwm.setVersion("1.0.2-SNAPSHOT");

        dependencies.add(xfwm);

        repo_xfw = new Repository();
        repo_xfw.setName("Xtecuan.org repository");
        repo_xfw.setId("xtecuan.org");
        repo_xfw.setUrl("http://xtecuan.org/xtecuannet_framework/repository");

        repos.add(repo_xfw);
    }

    @Override
    public void filloutTemplate() {


        try {

            File appDir = FillerUtils.config.getPathApp();
            File pom = new File(appDir, POM_XML);

            if (pom.exists()) {
                logger.info("Processing POM model file: " + pom.getPath());
                Reader reader = new FileReader(pom);
                MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
                Model model = xpp3Reader.read(reader);

                String group = model.getGroupId();
                String artifact = model.getArtifactId();

                logger.info("Processing group: " + group + " artifact: " + artifact);

                for (Repository repository : repos) {
                    logger.info("Adding repository: " + repository.toString());
                    model.addRepository(repository);
                }

                for (Dependency dependency : dependencies) {
                    logger.info("Adding dependency: " + dependency.toString());
                    model.addDependency(dependency);
                }

                logger.info("Writing new " + POM_XML + " config");

                MavenXpp3Writer xpp3Writer = new MavenXpp3Writer();
                xpp3Writer.write(new FileWriter(pom), model);

                logger.info("......done");
            }



        } catch (Exception ex) {
            logger.error("Error al generar el archivo de template: " + TEMPLATE_NAME, ex);
        }
    }

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }

//    public static void main(String[] args) {
//        new PomXmlModelEnhancerFiller().filloutTemplate();
//    }

//    public static void main1(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {
//
//        File file = new File("C:\\Documents and Settings\\Administrador\\Mis documentos\\NetBeansProjects\\ejemplo-modelo\\pom.xml");
//        Reader reader = new FileReader(file);
//        try {
//            MavenXpp3Reader xpp3Reader = new MavenXpp3Reader();
//            Model model = xpp3Reader.read(reader);
//
//            String group = model.getGroupId();
//            String artifact = model.getArtifactId();
//            Dependency d = new Dependency();
//            d.setGroupId("com.xtesoft.xtecuannet.framework");
//            d.setArtifactId("xtecuannet-framework-model-mvn");
//            d.setVersion("1.0.2-SNAPSHOT");
//            model.addDependency(d);
//
//            Repository repo = new Repository();
//            repo.setName("Xtecuan.org repository");
//            repo.setId("xtecuan.org");
//            repo.setUrl("http://xtecuan.org/xtecuannet_framework/repository");
//            model.addRepository(repo);
//
//
//            MavenXpp3Writer xpp3Writer = new MavenXpp3Writer();
//            xpp3Writer.write(new FileWriter(file), model);
//
//
//            System.out.println(group);
//            System.out.println(artifact);
//        } finally {
//            reader.close();
//        }
//    }
}
