/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.utils;

//import com.xtesoft.xtecuannet.framework.model.entities.SecMenu;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

            Class[] array1 = null;

            Method method = instance.getClass().getMethod(generateGetterName(name), array1);

            Object[] array = null;

            salida = method.invoke(instance, array);
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

    public static boolean isNullable(Field field) {

        boolean isNullable = true;

        boolean column = field.isAnnotationPresent(Column.class);

        if (column) {

            Column col = field.getAnnotation(Column.class);

            isNullable = col.nullable();

        }

        return isNullable;
    }

    public static int getColumnMaxLength(Field field) {

        int maxlength = -1;

        boolean column = field.isAnnotationPresent(Column.class);

        if (column) {

            Column col = field.getAnnotation(Column.class);

            maxlength = col.length();
        }

        return maxlength;
    }

    public static boolean isInsertable(Field field) {
        boolean isInsertable = false;

        boolean column = field.isAnnotationPresent(Column.class);

        if (column) {

            Column col = field.getAnnotation(Column.class);

            isInsertable = col.insertable();

        }

        return isInsertable;
    }

    public static boolean isUpdatable(Field field) {
        boolean isInsertable = false;

        boolean column = field.isAnnotationPresent(Column.class);

        if (column) {

            Column col = field.getAnnotation(Column.class);

            isInsertable = col.updatable();

        }

        return isInsertable;
    }

    public static boolean isOneToManyField(Field field) {

        boolean salida = false;

        salida = field.isAnnotationPresent(OneToMany.class);

        return salida;

    }

    public static Field getEmbeddedIdField(Class myClass) {
        Field field = null;
        Field[] fields = myClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field current = fields[i];
            if (current.isAnnotationPresent(EmbeddedId.class)) {
                field = current;
                break;
            }
        }
        return field;
    }

    public static Field getSimpleIdField(Class myClass) {
        Field field = null;
        Field[] fields = myClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field current = fields[i];
            if (current.isAnnotationPresent(Id.class)) {
                field = current;
                break;
            }
        }
        return field;
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

    public static List<Field> getManyToOneFields(Class clazz) {
        List<Field> salida = new ArrayList<Field>(0);

        Field[] all = clazz.getDeclaredFields();

        for (int i = 0; i < all.length; i++) {
            Field field = all[i];
            if (isManyToOneField(field)) {
                salida.add(field);
            }
        }

        return salida;
    }

    public static Constructor getDefaultConstructor(Class clazz) {

        Constructor constructor = null;

        Class[] params = null;
        try {
            constructor = clazz.getDeclaredConstructor(params);
        } catch (NoSuchMethodException ex) {
            logger.error("Error al extraer el constructor: ", ex);
        } catch (SecurityException ex) {
            logger.error("Error de seguridad al extraer el constructor: ", ex);
        }


        return constructor;
    }
    
    
    public static Object createEmptyEntityInstance(Class entityClass){
    
        Object salida = null;
        
        Field pkField = null;
        pkField = getEmbeddedIdField(entityClass);
        if (pkField == null) {
            pkField = getSimpleIdField(entityClass);
        }
        
        try{
            
            salida = entityClass.newInstance();
            
            if(isSimplePKField(pkField)){
                
                setPropertyToInstance(salida,pkField.getName(),pkField.getType().newInstance());
            }else if(isEmbeddedIdField(pkField)){
                setPropertyToInstance(salida, pkField.getName(), pkField.getType().newInstance());
                Object pkValue = getPropertyFromInstance(salida,pkField.getName());
                List<Field> pkFields = getOnlyFields(pkField.getType());
                for (Field pkInternalField : pkFields) {
                    setPropertyToInstance(pkValue, pkInternalField.getName(), pkInternalField.getType().newInstance());
                }
                
            }
            
            List<Field> entityMFields = getManyToOneFields(entityClass);
            
            for (Field mField : entityMFields) {
                ClassUtils.setPropertyToInstance(salida, mField.getName(), mField.getType().newInstance());
            }
            
            
        }catch(Exception ex){
            logger.error("Error al crear la instancia de la entidad: "+entityClass.getName(), ex);
        }
        
        return salida;
    }
    
    
    
//    public static void main(String[] args) {
//
//        Field[] fields = SecMenu.class.getDeclaredFields();
//
//
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            logger.info(field.getName() + " isManyToOneField: " + isManyToOneField(field));
//
//        }
//
//
//    }
//    public static void main(String[] args) {
//        Field[] fields = Tblcarrera.class.getDeclaredFields();
//
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//
//            logger.info("Field: " + field.getName());
//            logger.info(" is nullable: " + isNullable(field));
//            logger.info("Length: " + getColumnMaxLength(field));
//            logger.info("========================================");
//        }
//    }

//    public static void main(String[] args) {
//        Constructor c = getDefaultConstructor(CatCji.class);
//
//        Object[] args1 = new Object[0];
//        try {
//            Object instance = c.newInstance(args1);
//
//            if (instance != null) {
//                
//                
//                setPropertyToInstance(instance, "cji", "SOY");
//                logger.info(instance);
//                
//            } else {
//                logger.info("viene nula");
//            }
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalArgumentException ex) {
//            java.util.logging.Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvocationTargetException ex) {
//            java.util.logging.Logger.getLogger(ClassUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
