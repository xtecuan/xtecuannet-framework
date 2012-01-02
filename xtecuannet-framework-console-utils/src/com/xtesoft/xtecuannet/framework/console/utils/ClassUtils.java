/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import com.xtesoft.xtecuannet.framework.model.entities.SecMenu;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.apache.log4j.Logger;

/**
 *
 * @author xtecuan
 */
public final class ClassUtils {

    private static Logger logger = Logger.getLogger(ClassUtils.class);

    public static Class getFieldClass(String fieldName, Class clazz) {

        Class out = null;

        try {

            out = clazz.getDeclaredField(fieldName).getType();
        } catch (Exception ex) {

            logger.error("Error getting the Class of Field: " + fieldName, ex);
        }
        return out;
    }

    public static void setPropertyToInstance(Object instance, String name, Object value) {

        Object salida = null;
        try {

            Method method = instance.getClass().getMethod(generateSetterName(name), value.getClass());

            salida = method.invoke(instance, value);
        } catch (Exception ex) {

            logger.error("Error setting the property: " + name, ex);
        }

    }

    @SuppressWarnings("unchecked")
    public static Object getPropertyFromInstance(Object instance, String name) {

        Object salida = null;
        try {

            Method method = instance.getClass().getMethod(generateGetterName(name), null);

            salida = method.invoke(instance, null);
        } catch (Exception ex) {

            logger.error("Error getting the property: " + name, ex);
        }


        return salida;
    }

    public static String generateGetterName(String name) {

        String beanName = name;

        String correctedName = "get" + beanName.substring(0, 1).toUpperCase() + beanName.substring(1);

        return correctedName;

    }

    public static String generateSetterName(String name) {

        String beanName = name;

        String correctedName = "set" + beanName.substring(0, 1).toUpperCase() + beanName.substring(1);

        return correctedName;

    }

    public static Field getSimplePKFieldFromEntity(Class entity) {


        Field salida = null;
        Field[] field = entity.getDeclaredFields();

        for (int i = 0; i < field.length; i++) {
            Field current = field[i];

            if (current.isAnnotationPresent(Id.class)) {

                salida = current;
                break;
            }
        }

        return salida;

    }

    public static boolean isSimplePKFieldGenerated(Field field) {


        boolean salida = false;

        salida = field.isAnnotationPresent(GeneratedValue.class);

        return salida;

    }

    public static boolean isSimplePKField(Field field) {


        boolean salida = false;

        salida = field.isAnnotationPresent(Id.class);

        return salida;

    }

    public static boolean isManyToOneField(Field field) {

        boolean salida = false;

        salida = field.isAnnotationPresent(ManyToOne.class);

        return salida;
    }

    public static boolean isEmbeddedIdField(Field field) {

        boolean salida = false;

        salida = field.isAnnotationPresent(EmbeddedId.class);

        return salida;

    }
    
    
    
    

    public static void main(String[] args) {

        Field[] fields = SecMenu.class.getDeclaredFields();


        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            logger.info(field.getName() + " isManyToOneField: " + isManyToOneField(field));

        }


    }
}
