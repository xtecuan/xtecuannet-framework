/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.templater.constants;

import com.xtesoft.xtecuannet.framework.templater.config.TemplaterConfig;
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
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.log4j.Logger;
import org.xtecuan.xconfigurator.utils.ApacheConfig;
import org.xtecuan.xconfigurator.utils.LoggerUtilPlus;

/**
 *
 * @author xtecuan
 */
public final class Constants {

    public static final String APP_NAME = "xtecuannet-framework-templater";
    public static final String XML = ".xml";
    public static final String LOG_CONFIG = APP_NAME + XML;
    private static final Logger logger = LoggerUtilPlus.getLogger(Constants.class, LOG_CONFIG);
    private static PropertiesConfiguration config = iniConfig();

    private static PropertiesConfiguration iniConfig() {

        PropertiesConfiguration pc = null;

        try {
            pc = ApacheConfig.getPropertiesConfig(APP_NAME);
        } catch (ConfigurationException ex) {
            logger.error("Error al cargar la configuración: ", ex);
        }

        return pc;
    }

    public static PropertiesConfiguration getConfig() {
        return config;
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

        logger.info(getOnlyFields(TemplaterConfig.class));
    }
}
