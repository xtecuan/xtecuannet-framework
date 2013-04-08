/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtesoft.xtecuannet.framework.model.services;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author xtecuan
 */
public interface ReportsService {

    public static final String DOC = ".doc";
    public static final String RTF = ".rtf";
    public static final String PDF = ".pdf";
    public static final String REPORTS_PATH = "reports_path";
    public static final String SUBREPORT_DIR = "SUBREPORT_DIR";

    /**
     *
     * @return arreglo de byte[] con el reporte
     * @param extension
     * @param nombre
     */
    public byte[] generarReporteSinParametros(String nombre, String extension);

    /**
     * 
     * @param nombre
     * @param extension
     * @param data
     * @return
     */
    public byte[] generarReporteSinParametros(String nombre, String extension, Collection data);

    /**
     *
     * @return arreglo de byte[] con el reporte
     * @param pParametrosReporte
     * @param extension
     * @param nombre
     */
    public byte[] generarReporteConParametros(String nombre, String extension, Map pParametrosReporte);

    /**
     * 
     * @param nombre
     * @param extension
     * @param pParametrosReporte
     * @param data
     * @return
     */
    public byte[] generarReporteConParametros(String nombre, String extension, Map pParametrosReporte, Collection data);
}
