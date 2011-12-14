/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.config;

import com.xtesoft.xtecuannet.framework.templater.constants.Constants;
import com.xtesoft.xtecuannet.framework.utils.ClassUtils;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public class TemplaterConfig implements Serializable {

    private static Logger logger = Logger.getLogger(TemplaterConfig.class);
    public static final String SAMPLE_CONSTANT = "Sample";
    private String scriptName;
    private String scriptVersion;
    private File pathApp;
    private String basePkg;
    private String entitiesName;
    private String servicesName;
    private String entitiesPkg;
    private File entitiesPath;
    private String servicesPkg;
    private File servicesPath;
    private String parentService;
    private String parentServiceImport;
    private String serviceSuffixName;
    private File templatesPath;
    private String servicesNameImpl;
    private String servicesPkgImpl;
    private File servicesPathImpl;
    private String serviceSuffixNameImpl;
    private String parentServiceImpl;
    private String parentServiceImportImpl;
    private String springConfigXmlFilename;
    private String configPropsName;
    private String dataSourceName;
    private String jpaDatabaseName;
    private String showSQL;
    private String jpaPuName;
    private String jpaPuNameJndi;
    private String jpaPuNameJndiFull;
    private File reportsPath;
    private String webappDefaultSkin;
    private File excelPath;
    private File pgsqlBkpPath;
    private String pgsqlUser;
    private String pgsqlPass;
    private String pgsqlDbname;
    private String pgsqlBkpBaseFilename;
    private String pgsqlBkpExtension;
    private String pgsqlBkpCmd;
    private String pgsqlHost;
    private String pgsqlPort;
    private String xlistBeanEntitiesPkg;
    private String xlistBeanEnumPkg;
    private String mailSessionJndi;
    private String log4jConfFilename;
    private String baseWebPkg;
    private String jpaProvider;
    private String jpaConfigFile;
    private String jpaTargetServer;
    private String facesConfigFile;
    private String bundlePath;
    private String bundleName;
    private String bundleVar;
    private String variableResolverClass;
    private File webappPath;
    private String webFolder;
    private String webSrcFolder;
    private String webSetupFolder;
    private String springBackingBeansFile;
    private String webXmlFile;
    private String webappDisplayName;
    private String webappDesc;
    private String sessionTimeout;
    private String welcomePage;
    private String springframeworkConfigFiles;
    private Boolean exposeRemoteSpringServices;
    private String remotingPattern;
    private String facesUrlPattern;

    public TemplaterConfig() {
        this.readConfig();
    }

    public final void refreshConfig() {

        this.readConfig();
    }

    public final void readConfig() {
        try {

            List<Field> fields = Constants.getOnlyFields(this.getClass());

            for (Field field : fields) {

                if (field.getType().getName().equals("java.lang.String")) {

                    ClassUtils.setPropertyToInstance(this, field.getName(), Constants.getResolvedValue(field.getName(), this.getClass()));
                } else if (field.getType().getName().equals("java.io.File")) {
                    ClassUtils.setPropertyToInstance(this, field.getName(), new File(Constants.getResolvedValue(field.getName(), this.getClass())));
                }else if (field.getType().getName().equals("java.lang.Boolean")) {
                    ClassUtils.setPropertyToInstance(this, field.getName(), Boolean.valueOf(Constants.getResolvedValue(field.getName(), this.getClass())));
                }
            }


        } catch (Exception e) {
            logger.error("Error al configurar la aplicacion: ", e);
        }
    }

    public String getBasePkg() {
        return basePkg;
    }

    public void setBasePkg(String basePkg) {
        this.basePkg = basePkg;
    }

    public String getEntitiesName() {
        return entitiesName;
    }

    public void setEntitiesName(String entitiesName) {
        this.entitiesName = entitiesName;
    }

    public File getEntitiesPath() {
        return entitiesPath;
    }

    public void setEntitiesPath(File entitiesPath) {
        this.entitiesPath = entitiesPath;
    }

    public String getEntitiesPkg() {
        return entitiesPkg;
    }

    public void setEntitiesPkg(String entitiesPkg) {
        this.entitiesPkg = entitiesPkg;
    }

    public String getParentService() {
        return parentService;
    }

    public void setParentService(String parentService) {
        this.parentService = parentService;
    }

    public File getPathApp() {
        return pathApp;
    }

    public void setPathApp(File pathApp) {
        this.pathApp = pathApp;
    }

    public String getServiceSuffixName() {
        return serviceSuffixName;
    }

    public void setServiceSuffixName(String serviceSuffixName) {
        this.serviceSuffixName = serviceSuffixName;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public File getServicesPath() {
        return servicesPath;
    }

    public void setServicesPath(File servicesPath) {
        this.servicesPath = servicesPath;
    }

    public String getServicesPkg() {
        return servicesPkg;
    }

    public void setServicesPkg(String servicesPkg) {
        this.servicesPkg = servicesPkg;
    }

    public String getParentServiceImport() {
        return parentServiceImport;
    }

    public void setParentServiceImport(String parentServiceImport) {
        this.parentServiceImport = parentServiceImport;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptVersion() {
        return scriptVersion;
    }

    public void setScriptVersion(String scriptVersion) {
        this.scriptVersion = scriptVersion;
    }

    public File getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(File templatesPath) {
        this.templatesPath = templatesPath;
    }

    public String getParentServiceImpl() {
        return parentServiceImpl;
    }

    public void setParentServiceImpl(String parentServiceImpl) {
        this.parentServiceImpl = parentServiceImpl;
    }

    public String getParentServiceImportImpl() {
        return parentServiceImportImpl;
    }

    public void setParentServiceImportImpl(String parentServiceImportImpl) {
        this.parentServiceImportImpl = parentServiceImportImpl;
    }

    public String getServiceSuffixNameImpl() {
        return serviceSuffixNameImpl;
    }

    public void setServiceSuffixNameImpl(String serviceSuffixNameImpl) {
        this.serviceSuffixNameImpl = serviceSuffixNameImpl;
    }

    public String getServicesNameImpl() {
        return servicesNameImpl;
    }

    public void setServicesNameImpl(String servicesNameImpl) {
        this.servicesNameImpl = servicesNameImpl;
    }

    public File getServicesPathImpl() {
        return servicesPathImpl;
    }

    public void setServicesPathImpl(File servicesPathImpl) {
        this.servicesPathImpl = servicesPathImpl;
    }

    public String getServicesPkgImpl() {
        return servicesPkgImpl;
    }

    public void setServicesPkgImpl(String servicesPkgImpl) {
        this.servicesPkgImpl = servicesPkgImpl;
    }

    public String getSpringConfigXmlFilename() {
        return springConfigXmlFilename;
    }

    public void setSpringConfigXmlFilename(String springConfigXmlFilename) {
        this.springConfigXmlFilename = springConfigXmlFilename;
    }

    public String getConfigPropsName() {
        return configPropsName;
    }

    public void setConfigPropsName(String configPropsName) {
        this.configPropsName = configPropsName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public File getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(File excelPath) {
        this.excelPath = excelPath;
    }

    public String getJpaDatabaseName() {
        return jpaDatabaseName;
    }

    public void setJpaDatabaseName(String jpaDatabaseName) {
        this.jpaDatabaseName = jpaDatabaseName;
    }

    public String getJpaPuName() {
        return jpaPuName;
    }

    public void setJpaPuName(String jpaPuName) {
        this.jpaPuName = jpaPuName;
    }

    public String getJpaPuNameJndi() {
        return jpaPuNameJndi;
    }

    public void setJpaPuNameJndi(String jpaPuNameJndi) {
        this.jpaPuNameJndi = jpaPuNameJndi;
    }

    public String getJpaPuNameJndiFull() {
        return jpaPuNameJndiFull;
    }

    public void setJpaPuNameJndiFull(String jpaPuNameJndiFull) {
        this.jpaPuNameJndiFull = jpaPuNameJndiFull;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        TemplaterConfig.logger = logger;
    }

    public String getMailSessionJndi() {
        return mailSessionJndi;
    }

    public void setMailSessionJndi(String mailSessionJndi) {
        this.mailSessionJndi = mailSessionJndi;
    }

    public String getPgsqlBkpBaseFilename() {
        return pgsqlBkpBaseFilename;
    }

    public void setPgsqlBkpBaseFilename(String pgsqlBkpBaseFilename) {
        this.pgsqlBkpBaseFilename = pgsqlBkpBaseFilename;
    }

    public String getPgsqlBkpCmd() {
        return pgsqlBkpCmd;
    }

    public void setPgsqlBkpCmd(String pgsqlBkpCmd) {
        this.pgsqlBkpCmd = pgsqlBkpCmd;
    }

    public String getPgsqlBkpExtension() {
        return pgsqlBkpExtension;
    }

    public void setPgsqlBkpExtension(String pgsqlBkpExtension) {
        this.pgsqlBkpExtension = pgsqlBkpExtension;
    }

    public File getPgsqlBkpPath() {
        return pgsqlBkpPath;
    }

    public void setPgsqlBkpPath(File pgsqlBkpPath) {
        this.pgsqlBkpPath = pgsqlBkpPath;
    }

    public String getPgsqlDbname() {
        return pgsqlDbname;
    }

    public void setPgsqlDbname(String pgsqlDbname) {
        this.pgsqlDbname = pgsqlDbname;
    }

    public String getPgsqlHost() {
        return pgsqlHost;
    }

    public void setPgsqlHost(String pgsqlHost) {
        this.pgsqlHost = pgsqlHost;
    }

    public String getPgsqlPass() {
        return pgsqlPass;
    }

    public void setPgsqlPass(String pgsqlPass) {
        this.pgsqlPass = pgsqlPass;
    }

    public String getPgsqlPort() {
        return pgsqlPort;
    }

    public void setPgsqlPort(String pgsqlPort) {
        this.pgsqlPort = pgsqlPort;
    }

    public String getPgsqlUser() {
        return pgsqlUser;
    }

    public void setPgsqlUser(String pgsqlUser) {
        this.pgsqlUser = pgsqlUser;
    }

    public File getReportsPath() {
        return reportsPath;
    }

    public void setReportsPath(File reportsPath) {
        this.reportsPath = reportsPath;
    }

    public String getShowSQL() {
        return showSQL;
    }

    public void setShowSQL(String showSQL) {
        this.showSQL = showSQL;
    }

    public String getWebappDefaultSkin() {
        return webappDefaultSkin;
    }

    public void setWebappDefaultSkin(String webappDefaultSkin) {
        this.webappDefaultSkin = webappDefaultSkin;
    }

    public String getXlistBeanEntitiesPkg() {
        return xlistBeanEntitiesPkg;
    }

    public void setXlistBeanEntitiesPkg(String xlistBeanEntitiesPkg) {
        this.xlistBeanEntitiesPkg = xlistBeanEntitiesPkg;
    }

    public String getXlistBeanEnumPkg() {
        return xlistBeanEnumPkg;
    }

    public void setXlistBeanEnumPkg(String xlistBeanEnumPkg) {
        this.xlistBeanEnumPkg = xlistBeanEnumPkg;
    }

    public String getBaseWebPkg() {
        return baseWebPkg;
    }

    public void setBaseWebPkg(String baseWebPkg) {
        this.baseWebPkg = baseWebPkg;
    }

    public String getLog4jConfFilename() {
        return log4jConfFilename;
    }

    public void setLog4jConfFilename(String log4jConfFilename) {
        this.log4jConfFilename = log4jConfFilename;
    }

    public String getJpaProvider() {
        return jpaProvider;
    }

    public void setJpaProvider(String jpaProvider) {
        this.jpaProvider = jpaProvider;
    }

    public String getJpaConfigFile() {
        return jpaConfigFile;
    }

    public void setJpaConfigFile(String jpaConfigFile) {
        this.jpaConfigFile = jpaConfigFile;
    }

    public String getJpaTargetServer() {
        return jpaTargetServer;
    }

    public void setJpaTargetServer(String jpaTargetServer) {
        this.jpaTargetServer = jpaTargetServer;
    }

    public String getBundlePath() {
        return bundlePath;
    }

    public void setBundlePath(String bundlePath) {
        this.bundlePath = bundlePath;
    }

    public String getBundleVar() {
        return bundleVar;
    }

    public void setBundleVar(String bundleVar) {
        this.bundleVar = bundleVar;
    }

    public String getFacesConfigFile() {
        return facesConfigFile;
    }

    public void setFacesConfigFile(String facesConfigFile) {
        this.facesConfigFile = facesConfigFile;
    }

    public String getVariableResolverClass() {
        return variableResolverClass;
    }

    public void setVariableResolverClass(String variableResolverClass) {
        this.variableResolverClass = variableResolverClass;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getSpringBackingBeansFile() {
        return springBackingBeansFile;
    }

    public void setSpringBackingBeansFile(String springBackingBeansFile) {
        this.springBackingBeansFile = springBackingBeansFile;
    }

    public String getWebFolder() {
        return webFolder;
    }

    public void setWebFolder(String webFolder) {
        this.webFolder = webFolder;
    }

    public String getWebSetupFolder() {
        return webSetupFolder;
    }

    public void setWebSetupFolder(String webSetupFolder) {
        this.webSetupFolder = webSetupFolder;
    }

    public String getWebSrcFolder() {
        return webSrcFolder;
    }

    public void setWebSrcFolder(String webSrcFolder) {
        this.webSrcFolder = webSrcFolder;
    }

    public String getWebXmlFile() {
        return webXmlFile;
    }

    public void setWebXmlFile(String webXmlFile) {
        this.webXmlFile = webXmlFile;
    }

    public File getWebappPath() {
        return webappPath;
    }

    public void setWebappPath(File webappPath) {
        this.webappPath = webappPath;
    }

    public String getWebappDisplayName() {
        return webappDisplayName;
    }

    public void setWebappDisplayName(String webappDisplayName) {
        this.webappDisplayName = webappDisplayName;
    }

    public String getWebappDesc() {
        return webappDesc;
    }

    public void setWebappDesc(String webappDesc) {
        this.webappDesc = webappDesc;
    }

    public String getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Boolean getExposeRemoteSpringServices() {
        return exposeRemoteSpringServices;
    }

    public void setExposeRemoteSpringServices(Boolean exposeRemoteSpringServices) {
        this.exposeRemoteSpringServices = exposeRemoteSpringServices;
    }

    public String getRemotingPattern() {
        return remotingPattern;
    }

    public void setRemotingPattern(String remotingPattern) {
        this.remotingPattern = remotingPattern;
    }

    public String getSpringframeworkConfigFiles() {
        return springframeworkConfigFiles;
    }

    public void setSpringframeworkConfigFiles(String springframeworkConfigFiles) {
        this.springframeworkConfigFiles = springframeworkConfigFiles;
    }

    public String getWelcomePage() {
        return welcomePage;
    }

    public void setWelcomePage(String welcomePage) {
        this.welcomePage = welcomePage;
    }

    public String getFacesUrlPattern() {
        return facesUrlPattern;
    }

    public void setFacesUrlPattern(String facesUrlPattern) {
        this.facesUrlPattern = facesUrlPattern;
    }
    
    
}
