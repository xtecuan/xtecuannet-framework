/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class GeneratorConfig implements Serializable {

    private static Logger logger = Logger.getLogger(GeneratorConfig.class);
    private static final String DEFAULT_CONFIG = "./conf/xtecuannet-framework-codegenerator.config";
    private XConnEnum activeConnection;
    private String appName;
    private File appEarPath;
    private String appModelName;
    private File appModelPath;
    private String appModelPackage;
    private String appModelPentities;
    private String appModelPservices;
    private String appModelPservicesimpl;
    private String appModelSpringfilename;
    private String appWebName;
    private File appWebPath;
    private String appWebPackage;
    private String appWebPagesPath;
    private String appWebPagesGenerated;
    private String appWebSpringfilename;
    private String postgresqlUser;
    private String postgresqlPass;
    private String postgresqlUrl;
    private String mysqlUser;
    private String mysqlPass;
    private String mysqlUrl;
    private File templatesPath;
    private String basicTemplate;

    public GeneratorConfig() {
        this.readConfig(null);
    }

    public GeneratorConfig(String config) {

        this.readConfig(config);
    }

    private void readConfig(String config) {


        if (config == null) {

            config = DEFAULT_CONFIG;
        }

        try {
            File file = new File(config);
            FileInputStream fis = new FileInputStream(file);
            Properties props = new Properties();
            props.load(fis);

            for (Object object : props.keySet()) {
                String key = (String) object;

                Class current = ClassUtils.getFieldClass(key, GeneratorConfig.class);

                //logger.info(current.getName());

                if (current.getName().equals("java.lang.String")) {

                    ClassUtils.setPropertyToInstance(this, key, props.get(key));
                } else if (current.getName().equals("java.io.File")) {

                    ClassUtils.setPropertyToInstance(this, key, new File(props.getProperty(key)));
                } else {

                    ClassUtils.setPropertyToInstance(this, key, XConnEnum.getConfig(props.getProperty(key)));
                }


            }

        } catch (Exception ex) {
        }

    }

    public XConnEnum getActiveConnection() {
        return activeConnection;
    }

    public void setActiveConnection(XConnEnum activeConnection) {
        this.activeConnection = activeConnection;
    }

    public File getAppEarPath() {
        return appEarPath;
    }

    public void setAppEarPath(File appEarPath) {
        this.appEarPath = appEarPath;
    }

    public String getAppModelName() {
        return appModelName;
    }

    public void setAppModelName(String appModelName) {
        this.appModelName = appModelName;
    }

    public String getAppModelPackage() {
        return appModelPackage;
    }

    public void setAppModelPackage(String appModelPackage) {
        this.appModelPackage = appModelPackage;
    }

    public File getAppModelPath() {
        return appModelPath;
    }

    public void setAppModelPath(File appModelPath) {
        this.appModelPath = appModelPath;
    }

    public String getAppModelPentities() {
        return appModelPentities;
    }

    public void setAppModelPentities(String appModelPentities) {
        this.appModelPentities = appModelPentities;
    }

    public String getAppModelPservices() {
        return appModelPservices;
    }

    public void setAppModelPservices(String appModelPservices) {
        this.appModelPservices = appModelPservices;
    }

    public String getAppModelPservicesimpl() {
        return appModelPservicesimpl;
    }

    public void setAppModelPservicesimpl(String appModelPservicesimpl) {
        this.appModelPservicesimpl = appModelPservicesimpl;
    }

    public String getAppModelSpringfilename() {
        return appModelSpringfilename;
    }

    public void setAppModelSpringfilename(String appModelSpringfilename) {
        this.appModelSpringfilename = appModelSpringfilename;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppWebName() {
        return appWebName;
    }

    public void setAppWebName(String appWebName) {
        this.appWebName = appWebName;
    }

    public String getAppWebPackage() {
        return appWebPackage;
    }

    public void setAppWebPackage(String appWebPackage) {
        this.appWebPackage = appWebPackage;
    }

    public String getAppWebPagesGenerated() {
        return appWebPagesGenerated;
    }

    public void setAppWebPagesGenerated(String appWebPagesGenerated) {
        this.appWebPagesGenerated = appWebPagesGenerated;
    }

    public String getAppWebPagesPath() {
        return appWebPagesPath;
    }

    public void setAppWebPagesPath(String appWebPagesPath) {
        this.appWebPagesPath = appWebPagesPath;
    }

    public File getAppWebPath() {
        return appWebPath;
    }

    public void setAppWebPath(File appWebPath) {
        this.appWebPath = appWebPath;
    }

    public String getAppWebSpringfilename() {
        return appWebSpringfilename;
    }

    public void setAppWebSpringfilename(String appWebSpringfilename) {
        this.appWebSpringfilename = appWebSpringfilename;
    }

    public String getMysqlPass() {
        return mysqlPass;
    }

    public void setMysqlPass(String mysqlPass) {
        this.mysqlPass = mysqlPass;
    }

    public String getMysqlUrl() {
        return mysqlUrl;
    }

    public void setMysqlUrl(String mysqlUrl) {
        this.mysqlUrl = mysqlUrl;
    }

    public String getMysqlUser() {
        return mysqlUser;
    }

    public void setMysqlUser(String mysqlUser) {
        this.mysqlUser = mysqlUser;
    }

    public String getPostgresqlPass() {
        return postgresqlPass;
    }

    public void setPostgresqlPass(String postgresqlPass) {
        this.postgresqlPass = postgresqlPass;
    }

    public String getPostgresqlUrl() {
        return postgresqlUrl;
    }

    public void setPostgresqlUrl(String postgresqlUrl) {
        this.postgresqlUrl = postgresqlUrl;
    }

    public String getPostgresqlUser() {
        return postgresqlUser;
    }

    public void setPostgresqlUser(String postgresqlUser) {
        this.postgresqlUser = postgresqlUser;
    }

    public File getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(File templatesPath) {
        this.templatesPath = templatesPath;
    }

    public String getBasicTemplate() {
        return basicTemplate;
    }

    public void setBasicTemplate(String basicTemplate) {
        this.basicTemplate = basicTemplate;
    }

    @Override
    public String toString() {
        return "GeneratorConfig{" + "activeConnection=" + activeConnection + "\nappName=" + appName + "\nappEarPath=" + appEarPath + "\nappModelName=" + appModelName + "\nappModelPath=" + appModelPath + "\nappModelPackage=" + appModelPackage + "\nappModelPentities=" + appModelPentities + "\nappModelPservices=" + appModelPservices + "\nappModelPservicesimpl=" + appModelPservicesimpl + "\nappModelSpringfilename=" + appModelSpringfilename + "\nappWebName=" + appWebName + "\nappWebPath=" + appWebPath + "\nappWebPackage=" + appWebPackage + "\nappWebPagesPath=" + appWebPagesPath + "\nappWebPagesGenerated=" + appWebPagesGenerated + "\nappWebSpringfilename=" + appWebSpringfilename + "\npostgresqlUser=" + postgresqlUser + "\npostgresqlPass=" + postgresqlPass + "\npostgresqlUrl=" + postgresqlUrl + "\nmysqlUser=" + mysqlUser + "\nmysqlPass=" + mysqlPass + "\nmysqlUrl=" + mysqlUrl + "\ntemplatesPath=" + templatesPath + "\nbasicTemplate=" + basicTemplate + '}';
    }

//    public static void main(String[] args) {
//
//        GeneratorConfig instance = new GeneratorConfig();
//
//        logger.info(instance);
//
//    }
}
