/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.console.utils;

import com.xtesoft.xtecuannet.framework.utils.ClassUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import sv.edu.ues.siaarafia.modelo.entidades.Tblcarrera;

/**
 *
 * @author xtecuan
 */
public class Test {

    public static boolean validateEntity(Object entity, Map<String, Object> toValidate) {

        boolean state = false;

        Class eclass = entity.getClass();



        List<String> names = (List<String>) toValidate.get("names");

        Field[] fields = new Field[names.size()];


        int j = 0;
        for (String name : names) {

            try {

                fields[j] = eclass.getDeclaredField(name);

            } catch (Exception e) {
            }

            j++;
        }


        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            Object value = null;

            if (ClassUtils.isSimplePKField(field)) {

                if (ClassUtils.isSimplePKFieldGenerated(field)) {

                    System.out.println("PK generated Name: " + field.getName());
                    state = state || true;

                    System.out.println("State: " + state);
                } else {

                    System.out.println("PK entered Name: " + field.getName());

                    value = ClassUtils.getPropertyFromInstance(entity, field.getName());

                    if (value != null) {

                        if (value instanceof String) {

                            String str = (String) value;

                            state = state || str.length() > 0;

                            int maxlength = ClassUtils.getColumnMaxLength(field);

                            state = state || str.length() <= maxlength;

                        } else {

                            state = state || true;
                        }

                    } else {

                        state = state || false;
                    }

                    System.out.println("State: " + state);
                }



            } else if (ClassUtils.isEmbeddedIdField(field)) {
            } else if (ClassUtils.isOneToManyField(field)) {
            } else if (ClassUtils.isManyToOneField(field)) {
            } else {
                
                 System.out.println("Normal Field entered Name: " + field.getName());
            }

        }


        return state;

    }

    public static void main(String[] args) {

        Tblcarrera c = new Tblcarrera();
        c.setCcodigocarrera("A10507");
        c.setCnombrecarrera("ARQUITECTURA");

        Map<String, Object> rules = new HashMap<String, Object>();

        List<String> namesToValidate = new ArrayList<String>();

        namesToValidate.add("ccodigocarrera");
        namesToValidate.add("cnombrecarrera");

        rules.put("names", namesToValidate);



        System.out.println("Validate Result: " + validateEntity(c, rules));


//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("js");
//        try {
//            Object result = engine.eval("j=11;x=6; j+x;");
//
//            System.out.println(result);
//        } catch (ScriptException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
