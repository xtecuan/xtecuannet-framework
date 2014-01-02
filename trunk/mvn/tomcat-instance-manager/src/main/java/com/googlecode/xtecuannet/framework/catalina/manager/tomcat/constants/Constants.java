/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.catalina.manager.tomcat.constants;

import com.googlecode.xtecuannet.framework.catalina.manager.tomcat.utils.config.ServerXmlConfig;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author julianr
 */
public final class Constants {

    public static final String PROPERTIES = ".properties";
    private static final Log logger = LogFactory
            .getLog(Constants.class);
    private static PropertiesConfiguration config = iniConfig();

    private static PropertiesConfiguration iniConfig() {

        PropertiesConfiguration pc = null;

//        try {
//            pc = ApacheConfig.getPropertiesConfig(APP_NAME);
//        } catch (ConfigurationException ex) {
//            logger.error("Error al cargar la configuraciÃ³n: ", ex);
//        }
        return pc;
    }

    public static PropertiesConfiguration getConfig(String basePath, Class clazze) {
        PropertiesConfiguration pc = null;

        String className = clazze.getSimpleName();

        File basePathObj = new File(basePath);
        File fileCfg = new File(basePathObj, className + PROPERTIES);

        try {

            if (!basePathObj.exists()) {
                if (basePathObj.mkdirs()) {
                    logger.info(basePath + " created!!!");
                }
            } else {

                logger.info(basePath + " already exists skipping!!!");

                if (!fileCfg.exists()) {

                    FileUtils.touch(fileCfg);
                    logger.info(fileCfg.getName() + " created!!!");
                } else {

                    logger.info(fileCfg.getName() + " already exists skipping!!!");
                }
            }

            pc = new PropertiesConfiguration(fileCfg);
            pc.setReloadingStrategy(new FileChangedReloadingStrategy());
//            pc.setAutoSave(true);
            pc.setDelimiterParsingDisabled(false);

            logger.info(fileCfg.getName() + " loaded!!!");

        } catch (IOException e) {
            logger.error("Error creating config file or directory for class: " + className, e);
        } catch (ConfigurationException ex) {
            logger.error("Error getting/initializacion config for class: " + className, ex);
        }

        return pc;
    }

    public static PropertiesConfiguration getConfig() {
        return config;
    }

    public static String getResolvedValue(String elKey, Class toResolve, PropertiesConfiguration cfg) {
        String out = null;
        String value = cfg.getString(elKey);
        if (value.contains("${")) {
            Map map = generateMapFromConfig(toResolve);
            StrSubstitutor subs = new StrSubstitutor(map);
            subs.setEnableSubstitutionInVariables(true);
            out = subs.replace(value);
            if (out != null && out.contains("${")) {
                out = evaluateTransformationFunction(out);
            }
        } else {

            out = value;
        }
        return out;
    }

    public static String getResolvedValue(String elKey, Class toResolve) {
        String out = null;
        String value = getConfig().getString(elKey);
        if (value.contains("${")) {
            Map map = generateMapFromConfig(toResolve);
            StrSubstitutor subs = new StrSubstitutor(map);
            subs.setEnableSubstitutionInVariables(true);
            out = subs.replace(value);
            if (out != null && out.contains("${")) {
                out = evaluateTransformationFunction(out);
            }
        } else {

            out = value;
        }
        return out;
    }

    public static String evaluateTransformationFunction(String value) {

        String out = value;

        int indexF = value.indexOf("{") + 1;
        int indexL = value.indexOf("}");

        String functionWithParams = value.substring(indexF, indexL);

        String functionName = functionWithParams.substring(0, functionWithParams.indexOf("("));

        String[] functionParams = functionWithParams.substring(
                functionWithParams.indexOf("(") + 1,
                functionWithParams.indexOf(")")).split(",");

        Method te = findMethodByName(functionName);

        String sal = invokeStringFunction(te, functionParams);

        String fff = value.substring(0, value.indexOf("$")) + sal;

        out = fff;

        return out;
    }

    public static String invokeStringFunction(Method m, String[] params) {

        String out = null;

        try {

            out = (String) m.invoke(null, (Object[]) params);

        } catch (Exception e) {
            logger.error("Error invoking function: " + m.getName() + " with params: " + Arrays.asList(params), e);
        }
        return out;
    }

    public static Method findMethodByName(String name) {

        Method out = null;
        Method[] method = Constants.class.getMethods();

        for (int i = 0; i < method.length; i++) {
            Method method1 = method[i];
            if (method1.getName().equals(name)) {

                out = method1;
                break;
            }
        }

        return out;
    }

    public static Map generateMapFromConfig(Class toResolve) {

        Map valuesMap = new HashMap();

        List<Field> fields = getOnlyFields(toResolve);

        for (Field field : fields) {

            String val = getConfig().getString(field.getName());
            if (val != null && val.length() > 0) {
                valuesMap.put(field.getName(), val);
            }
        }

        return valuesMap;
    }

    public static String toPath(String dotFormat) {

        return dotFormat.replaceAll("\\.", "/");
    }

    public static List<Field> getOnlyFields(Class clazz) {

        List<Field> salida = new ArrayList<Field>(0);

        Field[] all = clazz.getDeclaredFields();

        for (int i = 0; i < all.length; i++) {
            Field field = all[i];

            logger.debug(Modifier.toString(field.getModifiers()) + "-" + field.getModifiers());
            if (field.getModifiers() == Modifier.PRIVATE) {

                salida.add(field);
            }
        }

        return salida;
    }

    public static void main(String[] args) {
        logger.info(evaluateTransformationFunction("/home/xtecuan/AmandaHorasSociales/NetbeansProjects/sscsjapp/sscsjapp-model/src/${toPath(com.xtesoft.sscsj.model.entities)}"));

        logger.info(getOnlyFields(ServerXmlConfig.class));
    }
}
