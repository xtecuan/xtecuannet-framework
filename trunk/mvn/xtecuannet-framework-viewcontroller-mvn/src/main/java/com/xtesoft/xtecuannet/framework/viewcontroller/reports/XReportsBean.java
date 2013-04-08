/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.viewcontroller.reports;

import com.xtesoft.xtecuannet.framework.model.services.PersistenceService;
import com.xtesoft.xtecuannet.framework.model.services.ReportsService;
import com.xtesoft.xtecuannet.framework.utils.ClassUtils;
import com.xtesoft.xtecuannet.framework.viewcontroller.beans.XBaseBean;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xtesoft.xtecuannet.framework.viewcontroller.annotations.XFacesMessage;

/**
 *
 * @author xtecuan
 */
public class XReportsBean extends XBaseBean implements Serializable {

    private static final String QUERY = "getQuery";
    @Autowired
    private ReportsService serviceReportes;
    @Autowired
    private PersistenceService servicePersistencia;
    private String nombreReporte;
    private Class pojoParametrosReporte;
    private Class pojoColumnasReporte;
    private Object currentRep;
    private Object currentDto;
    private List<Object> listado = new ArrayList<Object>(0);

    public XReportsBean() {
    }

    public XReportsBean(String nombreReporte, Class pojoParametrosReporte, Class pojoColumnasReporte) {
        this.nombreReporte = nombreReporte;
        this.pojoParametrosReporte = pojoParametrosReporte;
        this.pojoColumnasReporte = pojoColumnasReporte;
    }

    public ReportsService getServiceReportes() {
        return serviceReportes;
    }

    public void setServiceReportes(ReportsService serviceReportes) {
        this.serviceReportes = serviceReportes;
    }

    private Map generateParametersMap() {

        Map params = new HashMap();

        Field[] fields = getPojoParametrosReporte().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            Object value = ClassUtils.getPropertyFromInstance(this.getCurrentRep(), field.getName());
            params.put(field.getName(), value);

        }
        return params;
    }

    public void generarReporte(ActionEvent event) {


        if (this.getCurrentRep() != null) {

            List<String> errores = this.validateCurrentRep();

            if (errores.isEmpty()) {

                byte[] reporte = this.getServiceReportes().generarReporteConParametros(this.getNombreReporte(), ReportsService.PDF, this.generateParametersMap());

                if (reporte != null) {

                    this.downloadFile(reporte, FilenameUtils.getBaseName(this.getNombreReporte()) + ".pdf");

                } else {

                    this.addMessage("Error al generar el reporte: " + FilenameUtils.getBaseName(this.getNombreReporte()) + ".pdf" + " verifique los parametros!!!");
                }
            } else {
                for (String error : errores) {
                    this.addMessage(error);
                }
            }


        } else {

            this.addMessage("Error al capturar los parametros del reporte: " + FilenameUtils.getBaseName(this.getNombreReporte()) + ".pdf" + " Favor verifique!!!");
        }



        FacesContext.getCurrentInstance().renderResponse();


    }

    private List<String> validateCurrentRep() {

        List<String> salida = new ArrayList<String>(0);


        if (this.getCurrentRep() != null) {

            Class clazze = this.getCurrentRep().getClass();

            Field[] myfields = clazze.getDeclaredFields();


            for (int i = 0; i < myfields.length; i++) {
                Field field = myfields[i];



                Object thevalue = ClassUtils.getPropertyFromInstance(this.getCurrentRep(), field.getName());

                if (thevalue == null) {

                    boolean r1 = field.isAnnotationPresent(XFacesMessage.class);

                    if (r1) {

                        XFacesMessage message = field.getAnnotation(XFacesMessage.class);

                        salida.add(message.message());

                    } else {
                        salida.add("Seleccione un valor para el parametro: " + field.getName());
                    }
                } else if (thevalue instanceof String) {

                    if (((String) thevalue).length() == 0) {

                        boolean r2 = field.isAnnotationPresent(XFacesMessage.class);

                        if (r2) {

                            XFacesMessage message = field.getAnnotation(XFacesMessage.class);

                            salida.add(message.message());

                        } else {
                            salida.add("Seleccione un valor para el parametro: " + field.getName());
                        }

                    }
                }

            }


        } else {

            salida.add("Los parametros del reporte deben ser ingresados correctamente");
        }


        return salida;
    }

    public void generarListado(ActionEvent event) {



        if (this.getCurrentRep() != null) {


            List<String> errores = this.validateCurrentRep();

            if (errores.isEmpty()) {

                this.listado = this.getServicePersistencia().doNativeQueryWithPojo(this.getQuery(), this.generateParametersMap(), this.getPojoColumnasReporte());

                if (listado.isEmpty()) {

                    this.addMessage("No se encontraron registros!!!");
                }

            } else {


                for (String error : errores) {
                    this.addMessage(error);
                }
            }
        }

        FacesContext.getCurrentInstance().renderResponse();
    }

    public String getQuery() {

        String salida = null;
        try {

            Class[] classes = null;

            Method method = this.getPojoColumnasReporte().getMethod(QUERY, classes);

            Object[] objects = null;

            salida = (String) method.invoke(null, objects);

        } catch (Exception e) {

            this.getLogger().error("Error al obtener el query del reporte: " + this.getNombreReporte(), e);
        }

        return salida;

    }

    public Object getCurrentRep() {

        if (currentRep == null) {
            try {
                this.currentRep = this.getPojoParametrosReporte().newInstance();
            } catch (InstantiationException ex) {
                this.getLogger().error("Error al crear la instancia de la clase: " + this.getPojoParametrosReporte().getName(), ex);
            } catch (IllegalAccessException ex) {
                this.getLogger().error("Error al crear la instancia de la clase: " + this.getPojoParametrosReporte().getName(), ex);
            }
        }

        return currentRep;
    }

    public void setCurrentRep(Object currentRep) {
        this.currentRep = currentRep;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public Class getPojoColumnasReporte() {
        return pojoColumnasReporte;
    }

    public void setPojoColumnasReporte(Class pojoColumnasReporte) {
        this.pojoColumnasReporte = pojoColumnasReporte;
    }

    public Class getPojoParametrosReporte() {
        return pojoParametrosReporte;
    }

    public void setPojoParametrosReporte(Class pojoParametrosReporte) {
        this.pojoParametrosReporte = pojoParametrosReporte;
    }

    public List<Object> getListado() {
        return listado;
    }

    public void setListado(List<Object> listado) {
        this.listado = listado;
    }

    public PersistenceService getServicePersistencia() {
        return servicePersistencia;
    }

    public void setServicePersistencia(PersistenceService servicePersistencia) {
        this.servicePersistencia = servicePersistencia;
    }

    public Object getCurrentDto() {
        if (currentDto == null) {
            try {
                //this.currentDto = this.getPojoColumnasReporte().newInstance();

                Constructor[] constructors = this.getPojoColumnasReporte().getConstructors();

                for (Constructor ccc : constructors) {


                    Field[] fields = this.getPojoColumnasReporte().getDeclaredFields();

                    Object[] args = new Object[fields.length - 1];

                    for (int i = 0; i < args.length; i++) {
                        args[i] = null;
                    }

                    this.currentDto = ccc.newInstance(args);

                }


            } catch (Exception ex) {
                this.getLogger().error("Error al crear la instancia de la clase: " + this.getPojoColumnasReporte().getName(), ex);
            }
        }

        return currentDto;
    }

    public void setCurrentDto(Object currentDto) {
        this.currentDto = currentDto;
    }
}
