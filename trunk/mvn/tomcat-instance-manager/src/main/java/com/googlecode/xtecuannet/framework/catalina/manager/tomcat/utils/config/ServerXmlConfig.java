/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.config;

import com.google.gson.Gson;
import com.googlecode.xtecuannet.framework.catalina.manager.tomcat.constants.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author julianr
 */
public class ServerXmlConfig extends GenericConfig implements Serializable {

    /*
     shutdownPort = 8005
     httpPort = 8080
     redirectPort = 8443
     ajpPort = 8009
     */
    public static final String D_INSTANCES_KEY = ServerXmlConfig.class.getSimpleName() + "_server";
    public static final String D_SHUTDOWN_PORT = "9005";
    public static final String D_HTTP_PORT = "9080";
    public static final String D_REDIRECT_PORT = "9443";
    public static final String D_AJP_PORT = "9009";
    public static final String D_INSTANCE_NAME = "instance";
    public static final Integer D_INSTANCE_NUMBER = Integer.valueOf("1");

    private String shutdownPort;
    private String httpPort;
    private String redirectPort;
    private String ajpPort;
    private String instanceName;
    private Integer instanceNumber;
    private String sitesPath;
    private static PropertiesConfiguration config;

    static {
        config = Constants.getConfig(getConfigPath(), ServerXmlConfig.class);
    }

    public static String fromServerXmlConfig2Str(ServerXmlConfig instanceS) {

        return new Gson().toJson(instanceS);
    }

    public static ServerXmlConfig fromStr2ServerXmlConfig(String instanceStr) {

        String f = instanceStr.replaceAll("\\\"", "");
        getLogger().info("+++++++++" + f);

        ServerXmlConfig s =null /*new Gson().fromJson(f, ServerXmlConfig.class)*/;

        return s;
    }

    public static List<ServerXmlConfig> fromJsonArray2ListObj(String[] json) {

        List<ServerXmlConfig> servers = new ArrayList<ServerXmlConfig>(0);

        for (int i = 0; i < json.length; i++) {
            String j = json[i];
            servers.add(fromStr2ServerXmlConfig(j));
        }

        return servers;

    }

    private static void evalCreation(ServerXmlConfig instance) {

        if (config != null) {
            getLogger().info("Iniciando evaluacion");
            String[] instances = config.getStringArray(D_INSTANCES_KEY);

            if (instances != null && instances.length > 0) {

                getLogger().info("En las instancias");

                List<ServerXmlConfig> sss = fromJsonArray2ListObj(instances);

                for (ServerXmlConfig s : sss) {

                    getLogger().info(s);
                }

            } else {
                try {
                    getLogger().info("Fijando la nueva instancia");
                    config.setProperty(D_INSTANCES_KEY, fromServerXmlConfig2Str(instance));
                    config.save();
                } catch (ConfigurationException ex) {
                    getLogger().error("Error saving the instance: " + instance.getInstanceName() + instance.getInstanceNumber(), ex);
                }
            }

        } else {
            getLogger().error("Error getting the config for class: " + ServerXmlConfig.class);
        }
    }

    public ServerXmlConfig() {
        shutdownPort = D_SHUTDOWN_PORT;
        httpPort = D_HTTP_PORT;
        redirectPort = D_REDIRECT_PORT;
        ajpPort = D_AJP_PORT;
        instanceName = D_INSTANCE_NAME;
        instanceNumber = D_INSTANCE_NUMBER;
        getLogger().info("Soy yo");
        evalCreation(this);
        getLogger().info("Soy yo saliendo");

    }

    public String getShutdownPort() {
        return shutdownPort;
    }

    public void setShutdownPort(String shutdownPort) {
        this.shutdownPort = shutdownPort;
    }

    public String getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }

    public String getRedirectPort() {
        return redirectPort;
    }

    public void setRedirectPort(String redirectPort) {
        this.redirectPort = redirectPort;
    }

    public String getAjpPort() {
        return ajpPort;
    }

    public void setAjpPort(String ajpPort) {
        this.ajpPort = ajpPort;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getSitesPath() {
        return sitesPath;
    }

    public void setSitesPath(String sitesPath) {
        this.sitesPath = sitesPath;
    }

    public static PropertiesConfiguration getConfig() {
        return config;
    }

    public Integer getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(Integer instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public static void main(String[] args) {
        String server = fromServerXmlConfig2Str(new ServerXmlConfig());
        System.out.println("Server JSON: " + server);

        ServerXmlConfig s = fromStr2ServerXmlConfig(server);

        System.out.println("Server toString: " + s);

    }

}
