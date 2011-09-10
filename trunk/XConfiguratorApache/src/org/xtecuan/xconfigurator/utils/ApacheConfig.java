/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xtecuan.xconfigurator.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

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

        logger.info(out.getPath() + " cargado!!!");

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

            logger.info("La carpeta ya existe: " + fpath.getName());

            if (f.exists()) {

                logger.info("El Archivo: " + f.getPath() + " ya existe");
            } else {

                touchCfgFile(f);
            }

        } else {

            logger.info("Creando la carpeta: " + fpath.getName());
            boolean result = fpath.mkdirs();

            if (result) {

                logger.info(fpath.getName() + " creada ...[done]");
                touchCfgFile(f);

            }

        }

        if (flog.getParentFile().exists()) {
            logger.info("La carpeta de log ya existe: " + flog.getParentFile().getName());
            if (flog.exists()) {

                logger.info("El Archivo de log: " + flog.getPath() + " ya existe");
            } else {

                touchLogFile(flog);
            }

        } else {

            logger.info("Creando la carpeta: " + flog.getParentFile().getName());
            boolean result = flog.getParentFile().mkdirs();

            if (result) {

                logger.info(flog.getParentFile().getName() + " creada ...[done]");
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
            logger.info("Archivo: " + config.getName() + " creado!!!");
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
            logger.info("Archivo: " + flog.getName() + " creado!!!");
            fw.flush();
            fw.close();

        } catch (IOException ex) {
            logger.error("Error al dar touch al archivo: " + flog.getPath(), ex);
        }

    }
//    public static void main(String[] args) {
//
//        PropertiesConfiguration c;
//        try {
//            c = ApacheConfig.getPropertiesConfig("xtecuanApp");
//            int pool = c.getInt("pool.size");
//
//            String ss = c.getString("datasource.sample");
//
//
//            logger.info(pool + " " + ss);
//        } catch (ConfigurationException ex) {
//            logger.error("Error al crear la configuracion: ", ex);
//        }
//
//
//    }
}
