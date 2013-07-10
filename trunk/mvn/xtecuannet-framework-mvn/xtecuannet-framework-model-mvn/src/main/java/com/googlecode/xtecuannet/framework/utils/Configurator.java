package com.googlecode.xtecuannet.framework.utils;

/**
 * Clase encargada de obtener la configuraciÃ³n para el mÃ³dulo de Reportes
 * @author <a href="mailto:xtecuan@gmail.com">Ing. Julian Rivera Pineda</a>
 */

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase encargada de obtener la configuración para el módulo de Reportes
 * @author <a href="mailto:xtecuan@gmail.com">Ing. Julian Rivera Pineda</a>
 */
public final class Configurator {
    private static Log logger = LogFactory.getLog(Configurator.class);
    private static String BUNDLE_CONFIG_CLASSPATH_PROPS="/configuration.properties";
    public static final Properties config1 = obtenerConfiguracionProps();

    public static final String obtenerEntradaConfig(String key) {

        //return config.getString(key);
        return config1.getProperty(key);

    }

    public static final Properties obtenerConfiguracionProps() {

        Properties props = new Properties();
        InputStream is =
            Configurator.class.getResourceAsStream(BUNDLE_CONFIG_CLASSPATH_PROPS);


        try {
            props.load(is);

        } catch (IOException e) {
            logger.error("Error al llenar el archivo de configuraciÃ³n del CSMS: ",
                         e);
        }
        //finally {

            return props;

       // }

    }

    public static void main(String[] args) {


        System.out.println(Configurator.obtenerEntradaConfig("reports_path"));
        //System.out.println(FilenameUtils.getBaseName("reporte_all_sec_users.jrxml"));

    }


}
