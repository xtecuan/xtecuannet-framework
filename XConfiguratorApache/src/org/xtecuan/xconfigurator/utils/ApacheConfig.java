/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.xconfigurator.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.xtecuan.xconfigurator.utils.enums.LogConfigType;

/**
 *
 * @author xtecuan
 * @version 0.0.1
 */
public final class ApacheConfig implements Serializable {

    private static final Logger logger = Logger.getLogger(ApacheConfig.class);
    private static final String HERE = ".";
    private static final String PROPERTIES = ".properties";
    private static final String LOG_FOLDER = "logs";
    private static final String LOG = ".log";
    private static final String XML = ".xml";
    private static final String AppConsoleAppender_template = "AppConsoleAppender.xml";
    private static final String AppExternalFile_template = "AppExternalFile.xml";
    private static final String LOGFILE_KEY = "${log_filename_and_path}";
    private static final String BASE_PACKAGE_KEY = "${base_package}";

    public static PropertiesConfiguration getPropertiesConfig(String myAppName, String packageName, LogConfigType type) throws ConfigurationException {
        createLocations(myAppName, packageName, type);
        PropertiesConfiguration out = new PropertiesConfiguration(getFile(myAppName));

        out.setReloadingStrategy(new FileChangedReloadingStrategy());

        logger.debug(out.getPath() + " cargado!!!");

        return out;
    }

    /**
     * Método que sirve para obtener un objeto de configuración de Apache commons
     * PropertiesConfiguration
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     * @return un objeto de tipo PropertiesConfiguration
     * @throws ConfigurationException 
     */
    public static PropertiesConfiguration getPropertiesConfig(String myAppName) throws ConfigurationException {

        createLocations(myAppName);
        PropertiesConfiguration out = new PropertiesConfiguration(getFile(myAppName));

        out.setReloadingStrategy(new FileChangedReloadingStrategy());

        logger.debug(out.getPath() + " cargado!!!");

        return out;
    }

    /**
     * Devuelve El Nombre del PATH de configuración como String
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     * @return El Nombre del PATH de configuración como String
     */
    public static String getPath(String myAppName) {
        return HERE + File.separator + myAppName;
    }

    /**
     * Devuelve El Nombre del archivo de configuración de la aplicación 
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     * @return El Nombre del archivo de configuración de la aplicación 
     */
    public static String getFile(String myAppName) {

        String path = getPath(myAppName);
        String out = path + File.separator + myAppName + PROPERTIES;
        return out;

    }

    /**
     * Devuelve El Nombre del archivo de log de la aplicación 
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     * @return El Nombre del archivo de log de la aplicación 
     */
    public static String getLogFile(String myAppName) {

        String path = getPath(myAppName);
        String out = path + File.separator + LOG_FOLDER + File.separator + myAppName + LOG;
        return out;
    }

    /**
     * Devuelve El Nombre del archivo de configuración del Log
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     * @return El nombre del archivo de configuaración del log de la aplicación
     */
    public static String getLogConfigFile(String myAppName) {

        String path = getPath(myAppName);
        String out = path + File.separator + myAppName + XML;
        return out;

    }

    /**
     * Crea las carpetas de configuración si no existen de manera vacia para configurar
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     * @param packageName el nombre del paquete por default de la aplicación
     * @param type Tipo de configuración de log
     */
    public static void createLocations(String myAppName, String packageName, LogConfigType type) {

        createLocations(myAppName);
        String configlog = getLogConfigFile(myAppName);
        File fclog = new File(configlog);
        if (fclog.getParentFile().exists()) {
            logger.debug("La carpeta de log ya existe: " + fclog.getParentFile().getName());
            if (fclog.exists()) {
                logger.debug("El Archivo de log: " + fclog.getPath() + " ya existe");
            } else {
                touchLogConfigFile(fclog, packageName, type, myAppName);
            }
        } else {
            logger.debug("Creando la carpeta: " + fclog.getParentFile().getName());
            boolean result = fclog.getParentFile().mkdirs();
            if (result) {
                logger.debug(fclog.getParentFile().getName() + " creada ...[done]");
                touchLogConfigFile(fclog, packageName, type, myAppName);
            }
        }


    }

    /**
     * Crea las carpetas de configuración si no existen de manera vacia para configurar
     * y le da touch a los archivos de configuración y de log de la aplicación
     * 
     * @param myAppName Aquí se escribe el nombre de la aplicación a configurar
     */
    public static void createLocations(String myAppName) {

        String path = getPath(myAppName);
        String config = getFile(myAppName);
        String log = getLogFile(myAppName);
        File f = new File(config);
        File fpath = new File(path);
        File flog = new File(log);
        if (fpath.exists()) {
            logger.debug("La carpeta ya existe: " + fpath.getName());
            if (f.exists()) {
                logger.debug("El Archivo: " + f.getPath() + " ya existe");
            } else {
                touchCfgFile(f);
            }
        } else {
            logger.debug("Creando la carpeta: " + fpath.getName());
            boolean result = fpath.mkdirs();
            if (result) {
                logger.debug(fpath.getName() + " creada ...[done]");
                touchCfgFile(f);
            }
        }
        if (flog.getParentFile().exists()) {
            logger.debug("La carpeta de log ya existe: " + flog.getParentFile().getName());
            if (flog.exists()) {
                logger.debug("El Archivo de log: " + flog.getPath() + " ya existe");
            } else {
                touchLogFile(flog);
            }
        } else {
            logger.debug("Creando la carpeta: " + flog.getParentFile().getName());
            boolean result = flog.getParentFile().mkdirs();
            if (result) {
                logger.debug(flog.getParentFile().getName() + " creada ...[done]");
                touchLogFile(flog);

            }
        }

    }

    /**
     * Creación de un archivo de configuración vacio
     * @param config objeto File apuntando al archivo de configuración
     */
    private static void touchCfgFile(File config) {
        try {
            FileWriter fw = new FileWriter(config);
            fw.write("###ApacheConfig generated config file for Application: " + config.getName());
            logger.debug("Archivo: " + config.getName() + " creado!!!");
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            logger.error("Error al dar touch al archivo: " + config.getPath(), ex);
        }
    }

    /**
     * Creación de un archivo de log vacio
     * @param flog objeto File apuntando al archivo de log
     */
    private static void touchLogFile(File flog) {

        try {
            FileWriter fw = new FileWriter(flog);
            fw.write("###ApacheConfig log file for Application: " + flog.getName());
            logger.debug("Archivo: " + flog.getName() + " creado!!!");
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            logger.error("Error al dar touch al archivo: " + flog.getPath(), ex);
        }

    }

    /**
     * Creación de archivo de configuración 
     * @param fclog
     * @param packageName
     * @param type 
     */
    private static void touchLogConfigFile(File fclog, String packageName, LogConfigType type, String myAppName) {

        try {
            URL url = LoggerUtilPlus.getUrl(type.getTemplate());

            logger.info(url);
            
            
            File template = FileUtils.toFile(url);

            List linesTemplate = FileUtils.readLines(template);
            List linesConfig = new ArrayList<String>(0);

            for (Object current : linesTemplate) {
                String str = (String) current;
                if (type.getCod().intValue() == 1) {
                    String salida = str.replace(LOGFILE_KEY, getLogFile(myAppName));
                    salida = salida.replace(BASE_PACKAGE_KEY, packageName);
                    linesConfig.add(salida);
                } else if (type.getCod().intValue() == 2) {
                    String salida = str.replace(BASE_PACKAGE_KEY, packageName);
                    linesConfig.add(salida);
                }
            }

            FileUtils.writeLines(fclog, linesConfig);

        } catch (Exception ex) {
            logger.error("Error al dar touch al archivo: " + fclog.getPath(), ex);
        }
    }

    public static void main(String[] args) {

        PropertiesConfiguration c;
        try {
            c = ApacheConfig.getPropertiesConfig("xtecuanApp", "org.xtecuan.xconfigurator", LogConfigType.AppFileAppender);
            int pool = c.getInt("pool.size");

            String ss = c.getString("datasource.sample");

            Logger log1 = LoggerUtilPlus.getLogger(ApacheConfig.class, new File(getLogConfigFile("xtecuanApp")));

            log1.info(pool + " " + ss);
        } catch (ConfigurationException ex) {
            logger.error("Error al crear la configuracion: ", ex);
        }
        
        //touchLogConfigFile(new File(getLogConfigFile("xtecuanApp")), "sample", LogConfigType.AppFileAppender  ,"xtecuanApp");


    }
//
//    public static void main2(String[] args) {
//        Logger log = LoggerUtilPlus.getLogger(ApacheConfig.class, "xtecuanApp.xml");
//
//        for (int i = 0; i < 1000; i++) {
//            logger.info("item: " + (i + 1));
//
//        }
//    }
}
