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

        File cfcBeansPath = FillerUtils.config.getCfcBeansPath();
        String cfcSQLDriver = FillerUtils.config.getCfcSQLDriver();
        String cfcSQLUrl = FillerUtils.config.getCfcSQLUrl();
        String cfcSQLUser = FillerUtils.config.getCfcSQLUser();
        String cfcSQLPass = FillerUtils.config.getCfcSQLPass();
        String sqlTables = FillerUtils.config.getCfcSQLTables();
        logger.info(sqlTables);
        String[] cfcSQLTables = StringUtils.split(sqlTables, ":");

        String cfcBeansPrefix = FillerUtils.config.getCfcBeansPrefix();
        String cfcBeansExtends = FillerUtils.config.getCfcBeansExtends();
        Map root = FillerUtils.getRootConfigAsMap();
        root.put("FilenameUtils", FillerUtils.getModelFor("org.apache.commons.io.FilenameUtils"));
        root.put("autor", System.getProperty("user.name"));
        root.put("extendsCfc", cfcBeansExtends);
        SQLScanner scanner = new SQLScanner(cfcSQLDriver, cfcSQLUrl, cfcSQLUser, cfcSQLPass);
        try {
            logger.info(cfcSQLTables.length);
            for (String cfcSQLTable : cfcSQLTables) {
                logger.info(cfcSQLTable);
                String cfcName = cfcBeansPrefix + cfcSQLTable.substring(cfcSQLTable.lastIndexOf(".") + 1);

                File cfcBean = new File(cfcBeansPath, cfcName + ".cfc");

                if (root.containsKey("cfcName")) {
                    root.replace("cfcName", cfcName);
                } else {
                    root.put("cfcName", cfcName);
                }

                if (root.containsKey("cfcSQLTable")) {
                    root.replace("cfcSQLTable", cfcSQLTable);
                } else {
                    root.put("cfcSQLTable", cfcSQLTable);
                }
                List<SQLField> cfcProperties = scanner.getSQLFields(cfcSQLTable);

                logger.info(cfcProperties);

                if (root.containsKey("cfcProperties")) {
                    root.replace("cfcProperties", cfcProperties);
                } else {
                    root.put("cfcProperties", cfcProperties);
                }
                if (!cfcBean.exists()) {

                    Writer out = new FileWriter(cfcBean);
                    template.process(root, out);
                    out.flush();
                } else {
                    logger.info("File: " + cfcBean.getName() + " already exists!!!!");
                }
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
