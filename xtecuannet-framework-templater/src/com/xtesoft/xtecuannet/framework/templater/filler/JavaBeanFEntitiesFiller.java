/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.filler;

import com.xtesoft.xtecuannet.framework.templater.filler.interfaces.TemplaterFiller;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.FillerUtils;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.SQLField;
import com.xtesoft.xtecuannet.framework.templater.filler.utils.SQLScanner;
import freemarker.template.Template;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class JavaBeanFEntitiesFiller implements TemplaterFiller {

    public static final String TEMPLATE_NAME = "FakeEntityJavaBean.ftl";
    private static final Logger logger = Logger.getLogger(JavaBeanFEntitiesFiller.class);

    @Override
    public void filloutTemplate() {
        Template template = FillerUtils.getTemplate(TEMPLATE_NAME);

        String javaBeansPackage = FillerUtils.config.getJavaBeansPackage();
        File javaBeansPath = FillerUtils.config.getJavaBeansPath();
        String javaSQLDriver = FillerUtils.config.getJavaSQLDriver();
        String javaSQLUrl = FillerUtils.config.getJavaSQLUrl();
        String javaSQLUser = FillerUtils.config.getJavaSQLUser();
        String javaSQLPass = FillerUtils.config.getJavaSQLPass();
        String sqlTables = FillerUtils.config.getJavaSQLTables();
        logger.info(sqlTables);
        String[] javaSQLTables = StringUtils.split(sqlTables, ":");

        String javaBeansPrefix = FillerUtils.config.getJavaBeansPrefix();
        String javaBeansImplementsClassName = FillerUtils.config.getJavaBeansImplementsClassName();
        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));
        root.put("autor", System.getProperty("user.name"));
        root.put("javaBeansImplementsClassName", javaBeansImplementsClassName);
        root.put("javaBeansPackage", javaBeansPackage);
        SQLScanner scanner = new SQLScanner(javaSQLDriver, javaSQLUrl, javaSQLUser, javaSQLPass);
        try {
            logger.info(javaSQLTables.length);
            int i = 0;
            for (String javaSQLTable : javaSQLTables) {
                logger.info(javaSQLTable);
                String javaName = javaBeansPrefix + javaSQLTable.substring(javaSQLTable.lastIndexOf(".") + 1);

                File javaBean = new File(javaBeansPath, javaName + ".java");

                if (root.containsKey("javaName")) {
                    root.replace("javaName", javaName);
                } else {
                    root.put("javaName", javaName);
                }

                String schemaName = javaSQLTable.substring(0, javaSQLTable.lastIndexOf("."));

                if (root.containsKey("schemaName")) {
                    root.replace("schemaName", schemaName);
                } else {
                    root.put("schemaName", schemaName);
                }

                String tableName = javaSQLTable.substring(javaSQLTable.lastIndexOf(".") + 1);

                if (root.containsKey("tableName")) {
                    root.replace("tableName", tableName);
                } else {
                    root.put("tableName", tableName);
                }

                if (root.containsKey("javaSQLTable")) {
                    root.replace("javaSQLTable", javaSQLTable);
                } else {
                    root.put("javaSQLTable", javaSQLTable);
                }
                List<SQLField> fakeentityProperties = scanner.getSQLFields(javaSQLTable);

                logger.info(fakeentityProperties);

                String idName = fakeentityProperties.get(0).getColumnName();

                if (root.containsKey("idName")) {
                    root.replace("idName", idName);
                } else {
                    root.put("idName", idName);
                }

                if (root.containsKey("fakeentityProperties")) {
                    root.replace("fakeentityProperties", fakeentityProperties);
                } else {
                    root.put("fakeentityProperties", fakeentityProperties);
                }

                List<SQLField> fakeentityImports = SQLField.getImportsFromList(fakeentityProperties);

                if (root.containsKey("fakeentityImports")) {
                    root.replace("fakeentityImports", fakeentityImports);
                } else {
                    root.put("fakeentityImports", fakeentityImports);
                }

                if (!javaBean.exists()) {

                    Writer out = new FileWriter(javaBean);
                    template.process(root, out);
                    out.flush();
                } else {
                    logger.info("File: " + javaBean.getName() + " already exists!!!!");
                }
                i++;
            }
            scanner.closeConnection();

        } catch (Exception e) {
            logger.error("Error processing template: " + getTemplateName(), e);
        }

    }

    @Override
    public String getTemplateName() {
        return TEMPLATE_NAME;
    }

    public static void main(String[] args) {
        File cfcBeansPath = FillerUtils.config.getCfcBeansPath();
        String cfcSQLDriver = FillerUtils.config.getCfcSQLDriver();
        String cfcSQLUrl = FillerUtils.config.getCfcSQLUrl();
        String cfcSQLUser = FillerUtils.config.getCfcSQLUser();
        String cfcSQLPass = FillerUtils.config.getCfcSQLPass();
        String[] cfcSQLTables = FillerUtils.config.getCfcSQLTables().split(",");
        String cfcBeansPrefix = FillerUtils.config.getCfcBeansPrefix();
        String cfcBeansExtends = FillerUtils.config.getCfcBeansExtends();

        logger.info(cfcSQLDriver + " " + cfcSQLUrl + " " + cfcSQLUser + " " + cfcSQLPass);

        logger.info("index = " + "Output.DimExternalEvents".substring("Output.DimExternalEvents".lastIndexOf(".") + 1));
        String tmp = "Output.DimExternalEvents,Output.LanguageInternalEvents,Registration.DimParticipants,Evaluation.FactEvaluationExternalEvents,Evaluation.FactEvalFacilitatorInternalEvents,General.DimInstitutions,Output.DimInternalEvents,Evaluation.FactEvaluationInternalEvents,Output.DimFacilitators,General.DimDate";
        String[] array = tmp.split(",");
        System.out.println("size:" + array.length);
        String[] tablas = StringUtils.split(tmp, ",");
        System.out.println("size:" + tablas.length);
    }
}
