/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.viewcontroller.beans;

import com.xtesoft.xtecuannet.framework.model.services.GenericService;
import com.xtesoft.xtecuannet.framework.utils.Configurator;
import com.xtesoft.xtecuannet.framework.viewcontroller.utils.ViewUtils;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author xtecuan
 */
public class XListBean extends XBaseBean implements Serializable {

    private static final String SERVICE = "Service";
    private static final String ENTITIES_PKG_KEY = "XListBean_entities_pkg";
    private static final String ENTITIES_PKG = Configurator.obtenerEntradaConfig(ENTITIES_PKG_KEY);
    private static final String VALUES = "values";
    private static final String ENUMS_PKG_KEY = "XListBean_enums_pkg";
    private static final String ENUMS_PKG = Configurator.obtenerEntradaConfig(ENUMS_PKG_KEY);

    private GenericService getClient(String service) {

        ApplicationContext ctx =
                WebApplicationContextUtils.getWebApplicationContext(this.getSession().getServletContext());

        return (GenericService) ctx.getBean(service);

    }

    public List<SelectItem> generateList(String pkg, String entityName, String label, String value) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        Class entity = null;

        try {
            entity = Class.forName(pkg + "." + entityName);
        } catch (Exception e) {

            this.getLogger().error("Error al cargar la clase de la entidad: ", e);
        }

        String serviceName = entity.getSimpleName() + SERVICE;

        List<Object> list = this.getClient(serviceName).findAll();

        items = ViewUtils.fromListToSelectItem(list, label, value);
        return items;

    }

    public List<SelectItem> generateList(String entityName, String label, String value) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        Class entity = null;

        try {
            entity = Class.forName(ENTITIES_PKG + "." + entityName);
        } catch (Exception e) {

            this.getLogger().error("Error al cargar la clase de la entidad: ", e);
        }

        String serviceName = entity.getSimpleName() + SERVICE;

        List<Object> list = this.getClient(serviceName).findAll();

        items = ViewUtils.fromListToSelectItem(list, label, value);
        return items;

    }

    public List<SelectItem> generateListEL(String entityName, String label, String value, boolean useValueEl) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        Class entity = null;

        try {
            entity = Class.forName(ENTITIES_PKG + "." + entityName);
        } catch (Exception e) {

            this.getLogger().error("Error al cargar la clase de la entidad: ", e);
        }

        String serviceName = entity.getSimpleName() + SERVICE;

        List<Object> list = this.getClient(serviceName).findAll();

        if (useValueEl) {
            items = ViewUtils.fromListToSelectItem(list, label, value, useValueEl);
        } else {
            items = ViewUtils.fromListToSelectItem(list, label, value);
        }
        return items;

    }

    public List<SelectItem> generateListCompoundLabelElWithService(GenericService service, String label, String value, boolean useValueEl) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        List<Object> list = service.findAll();

         if (useValueEl) {
            items = ViewUtils.fromListToSelectItemCompound(list, label, value, useValueEl);
        } else {
            items = ViewUtils.fromListToSelectItem(list, label, value);
        }
        
        
        return items;
    }

    public List<SelectItem> generateListCompoundLabelEL(String entityName, String label, String value, boolean useValueEl) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        Class entity = null;

        try {
            entity = Class.forName(ENTITIES_PKG + "." + entityName);
        } catch (Exception e) {

            this.getLogger().error("Error al cargar la clase de la entidad: ", e);
        }

        String serviceName = entity.getSimpleName() + SERVICE;

        List<Object> list = this.getClient(serviceName).findAll();

        if (useValueEl) {
            items = ViewUtils.fromListToSelectItemCompound(list, label, value, useValueEl);
        } else {
            items = ViewUtils.fromListToSelectItem(list, label, value);
        }
        return items;

    }

    public List<SelectItem> generateListFromEnum(String enumName, String label, String value) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        Class enumClass = null;

        try {
            enumClass = Class.forName(ENUMS_PKG + "." + enumName);
        } catch (Exception e) {

            this.getLogger().error("Error al cargar la clase de la entidad: ", e);
        }

        Class[] parameterTypes = null;
        Method method = null;

        try {
            method = enumClass.getMethod(VALUES, parameterTypes);

            Object[] parameters = null;

            Object[] myEnums = (Object[]) method.invoke(null, parameters);

            items = ViewUtils.fromListToSelectItem(myEnums, label, value);

        } catch (Exception e) {

            this.getLogger().info("Error loading Enum data: ", e);
        }



        return items;

    }

    public List<SelectItem> generateListFromEnum(String pkg, String enumName, String label, String value) {

        List<SelectItem> items = new ArrayList<SelectItem>(0);

        Class enumClass = null;

        try {
            enumClass = Class.forName(pkg + "." + enumName);
        } catch (Exception e) {

            this.getLogger().error("Error al cargar la clase de la entidad: ", e);
        }

        Class[] parameterTypes = null;
        Method method = null;

        try {
            method = enumClass.getMethod(VALUES, parameterTypes);

            Object[] parameters = null;

            Object[] myEnums = (Object[]) method.invoke(null, parameters);

            items = ViewUtils.fromListToSelectItem(myEnums, label, value);

        } catch (Exception e) {

            this.getLogger().info("Error loading Enum data: ", e);
        }



        return items;

    }
}