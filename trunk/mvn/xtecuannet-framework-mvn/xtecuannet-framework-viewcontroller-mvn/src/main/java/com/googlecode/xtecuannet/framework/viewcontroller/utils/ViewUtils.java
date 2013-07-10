/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.xtecuannet.framework.viewcontroller.utils;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author xtecuan
 */
public final class ViewUtils {

    public static List<SelectItem> fromListToSelectItem(Object[] list, String labelField, String valueField) {


        List<SelectItem> lista = new ArrayList<SelectItem>(0);

        for (Object element : list) {

            SelectItem item = new SelectItem();
            Object label = getPropertyFromInstance(element, labelField);
            Object value = getPropertyFromInstance(element, valueField);

            if (label != null) {

                item.setLabel((String) label);
                item.setDescription((String) label);
            }

            if (value != null) {
                item.setValue(value);
            }

            lista.add(item);

        }

        return lista;

    }

    public static List<SelectItem> fromListToSelectItemCompound(List<?> list, String labelField, String valueField, boolean useValueEl) {

        List<SelectItem> lista = new ArrayList<SelectItem>(0);

        if (useValueEl) {

            for (Object element : list) {
                SelectItem item = new SelectItem();

                String[] labelArray = labelField.split("[,]");

                Object label = null;



                if (labelArray != null && labelArray.length == 3) {
                    String[] labels = new String[labelArray.length];
                    for (int i = 0; i < labelArray.length - 1; i++) {
                        String labeli = labelArray[i];

                        if (labeli.contains(".")) {

                            String[] labeliArray = labeli.split("[.]");

                            Object p = getPropertyFromInstance(element, labeliArray[0]);

                            Object ch = getPropertyFromInstance(p, labeliArray[1]);

                            labels[i] = String.valueOf(ch);

                        } else {

                            labels[i] = String.valueOf(getPropertyFromInstance(element, labeli));
                        }
                    }

                    labels[2] = labelArray[2];

                    label = labels[0] + " " + labels[2] + " " + labels[1];

                }

                String[] valueArray = valueField.split("[.]");
                Object value = null;

                if (valueArray != null && valueArray.length == 2) {

                    Object parent = getPropertyFromInstance(element, valueArray[0]);

                    if (parent != null) {

                        value = getPropertyFromInstance(parent, valueArray[1]);
                    }

                } else {

                    value = getPropertyFromInstance(element, valueField);
                }

                if (label != null) {

                    if (label instanceof java.lang.String) {

                        item.setLabel((String) label);
                        item.setDescription((String) label);
                    } else {
                        item.setLabel(String.valueOf(label));
                        item.setDescription(String.valueOf(label));
                    }
                }

                if (value != null) {
                    item.setValue(value);
                }

                lista.add(item);


            }

        } else {
            lista = fromListToSelectItem(list, labelField, valueField);
        }

        return lista;

    }

    public static List<SelectItem> fromListToSelectItem(List<?> list, String labelField, String valueField, boolean useValueEl) {

        List<SelectItem> lista = new ArrayList<SelectItem>(0);

        if (useValueEl) {

            for (Object element : list) {
                SelectItem item = new SelectItem();
                Object label = getPropertyFromInstance(element, labelField);
                String[] valueArray = valueField.split("[.]");
                Object value = null;

                if (valueArray != null && valueArray.length == 2) {

                    Object parent = getPropertyFromInstance(element, valueArray[0]);

                    if (parent != null) {

                        value = getPropertyFromInstance(parent, valueArray[1]);
                    }

                }

                if (label != null) {

                    if (label instanceof java.lang.String) {

                        item.setLabel((String) label);
                        item.setDescription((String) label);
                    } else {
                        item.setLabel(String.valueOf(label));
                        item.setDescription(String.valueOf(label));
                    }
                }

                if (value != null) {
                    item.setValue(value);
                }

                lista.add(item);


            }

        } else {
            lista = fromListToSelectItem(list, labelField, valueField);
        }

        return lista;

    }

    public static List<SelectItem> fromListToSelectItem(List<?> list, String labelField, String valueField) {

        List<SelectItem> lista = new ArrayList<SelectItem>(0);

        for (Object element : list) {

            SelectItem item = new SelectItem();
            Object label = getPropertyFromInstance(element, labelField);
            Object value = getPropertyFromInstance(element, valueField);

            if (label != null) {

                if (label instanceof java.lang.String) {

                    item.setLabel((String) label);
                    item.setDescription((String) label);
                } else {
                    item.setLabel(String.valueOf(label));
                    item.setDescription(String.valueOf(label));
                }
            }

            if (value != null) {
                item.setValue(value);
            }

            lista.add(item);

        }

        return lista;

    }

    @SuppressWarnings("unchecked")
    public static Object getPropertyFromInstance(Object instance, String name) {

        Object salida = null;
        try {

            Class[] classes = null;

            Method method = instance.getClass().getMethod(generateGetterName(name), classes);

            Object[] objects = null;

            salida = method.invoke(instance, objects);
        } catch (Exception ex) {

            ex.printStackTrace();
        }


        return salida;
    }

    public static String generateGetterName(String name) {

        String beanName = name;

        String correctedName = "get" + beanName.substring(0, 1).toUpperCase() + beanName.substring(1);

        return correctedName;

    }

    public static List<SelectItem> generateLongSelectItem(Long start, Long finish) {

        List<SelectItem> lista = new ArrayList<SelectItem>(0);

        for (int i = start.intValue(); i <= finish.intValue(); i++) {

            SelectItem item = new SelectItem();

            item.setValue(Long.valueOf(i));
            item.setDescription(String.valueOf(i));
            item.setLabel(String.valueOf(i));

            lista.add(item);
        }

        return lista;

    }

    public static void main(String[] args) {

        String valueField = "sample.id";

        String[] valueArray = valueField.split("[.]");

        for (int i = 0; i < valueArray.length; i++) {
            String string = valueArray[i];
            System.out.println(string);
        }
    }
}
